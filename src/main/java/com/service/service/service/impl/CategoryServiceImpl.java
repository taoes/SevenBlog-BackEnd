package com.service.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.service.service.controller.resp.Category;
import com.service.service.mapper.CategoryMapper;
import com.service.service.mapper.dao.CategoryDO;
import com.service.service.service.CategoryService;
import com.service.service.service.converter.CategoryConverter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired private CategoryMapper categoryMapper;

  @Override
  public Map<String, List<Category>> all() {
    Wrapper<CategoryDO> queryWrapper =
        new LambdaQueryWrapper<CategoryDO>().orderByDesc(CategoryDO::getId);
    return categoryMapper.selectList(queryWrapper).stream()
        .map(CategoryConverter::of)
        .collect(Collectors.groupingBy(Category::getType));
  }

  @Override
  public List<Category> list() {
    Wrapper<CategoryDO> queryWrapper =
        new LambdaQueryWrapper<CategoryDO>().orderByDesc(CategoryDO::getId);
    return categoryMapper.selectList(queryWrapper).stream()
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
