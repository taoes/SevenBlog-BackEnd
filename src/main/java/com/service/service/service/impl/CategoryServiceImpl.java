package com.service.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.service.service.bean.FinalRedisKey;
import com.service.service.controller.resp.Category;
import com.service.service.mapper.CategoryMapper;
import com.service.service.mapper.dao.CategoryDO;
import com.service.service.service.CategoryService;
import com.service.service.service.converter.CategoryConverter;
import com.service.service.service.converter.JsonConverter;
import com.service.service.utils.RedisUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired private CategoryMapper categoryMapper;

  @Autowired private RedisUtil redisUtil;

  @Override
  public List<Category> all() {

    // 检查Redis中是否存在缓存信息
    String categoriesStr = redisUtil.get(FinalRedisKey.CATEGORY_REDIS_KEY, "all", String.class);
    if (StringUtils.hasText(categoriesStr)) {
      // 返序列化
      return JsonConverter.readListValue(categoriesStr, Category.class);
    }

    Wrapper<CategoryDO> queryWrapper =
        new LambdaQueryWrapper<CategoryDO>().orderByDesc(CategoryDO::getId);
    List<CategoryDO> categoryDOS = categoryMapper.selectList(queryWrapper);

    Map<String, List<Category>> categoryMap =
        categoryDOS.stream()
            .filter(categoryDO -> Objects.nonNull(categoryDO.getType()))
            .map(CategoryConverter::of)
            .collect(Collectors.groupingBy(Category::getType));

    List<Category> categoryList =
        categoryDOS.stream()
            .filter(categoryDO -> Objects.isNull(categoryDO.getType()))
            .map(CategoryConverter::of)
            .peek(
                category -> {
                  List<Category> categories =
                      categoryMap.getOrDefault(category.getKey(), new ArrayList<>(0));
                  category.setSub(categories);
                })
            .collect(Collectors.toList());
    // 保存到redis中
    categoriesStr = JsonConverter.toJSONString(categoryList);
    redisUtil.set(FinalRedisKey.CATEGORY_REDIS_KEY, "all",categoriesStr, 3600L);
    return categoryList;
  }

  @Override
  public List<Category> list() {
    Wrapper<CategoryDO> queryWrapper =
        new LambdaQueryWrapper<CategoryDO>().orderByDesc(CategoryDO::getId);
    List<CategoryDO> categoryDOS = categoryMapper.selectList(queryWrapper);

    return categoryDOS.stream()
        .filter(categoryDO -> Objects.nonNull(categoryDO.getType()))
        .map(CategoryConverter::of)
        .collect(Collectors.toList());
  }

  @Override
  public Category update(Category category) {
    CategoryDO categoryDO = CategoryConverter.of(category);

    if (category.getId() == null) {
      // 创建
      if (exist(category.getName())) {
        throw new RuntimeException("数据已经存在，无法新增");
      }
      categoryMapper.insert(categoryDO);
    } else {
      // 更新
      categoryMapper.updateById(categoryDO);
    }
    return getById(categoryDO.getId());
  }

  private Category getById(Long id) {
    return CategoryConverter.of(this.categoryMapper.selectById(id));
  }

  private boolean exist(String name) {
    Wrapper<CategoryDO> queryWrapper =
        new LambdaQueryWrapper<CategoryDO>().eq(CategoryDO::getName, name);
    return this.categoryMapper.selectCount(queryWrapper) > 0;
  }
}
