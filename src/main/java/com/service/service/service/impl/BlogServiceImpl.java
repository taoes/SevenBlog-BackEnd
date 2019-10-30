package com.service.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.service.service.controller.req.BlogReq;
import com.service.service.controller.resp.Blog;
import com.service.service.controller.resp.PageInfo;
import com.service.service.mapper.BlogMapper;
import com.service.service.mapper.dao.BlogDO;
import com.service.service.mapper.dao.KeyValue;
import com.service.service.service.BlogService;
import com.service.service.service.TagService;
import com.service.service.service.converter.BlogConverter;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class BlogServiceImpl implements BlogService {

  @Autowired private BlogMapper blogMapper;

  @Autowired TagService tagService;

  @Override
  public PageInfo<Blog> list(String searchKey, long pageNun, long pageSize, String blogType) {

    Wrapper<BlogDO> queryWrapper =
        new LambdaQueryWrapper<BlogDO>()
            .eq(StringUtils.hasText(blogType), BlogDO::getType, blogType)
            .orderByDesc(BlogDO::getId);
    IPage<BlogDO> blogDOIPage = blogMapper.selectPage(new Page<>(pageNun, pageSize), queryWrapper);

    List<BlogDO> records = blogDOIPage.getRecords();

    Collection<Blog> data = records.stream().map(BlogConverter::of).collect(Collectors.toList());

    return new PageInfo<Blog>()
        .setData(data)
        .setTotal(blogDOIPage.getTotal())
        .setPage(blogDOIPage.getSize());
  }

  @Override
  public List<Blog> listAll(String key) {
    Wrapper<BlogDO> queryWrapper =
        new LambdaQueryWrapper<BlogDO>()
            .eq(StringUtils.hasText(key), BlogDO::getTitle, key)
            .orderByDesc(BlogDO::getId);
    return this.blogMapper.selectList(queryWrapper).stream()
        .map(BlogConverter::simpleOf)
        .collect(Collectors.toList());
  }

  @Override
  public Blog getById(Long blogId) {
    BlogDO blogDO = blogMapper.selectById(blogId);
    if (blogDO == null) {
      throw new RuntimeException("文章不存在，获取失败");
    }
    return BlogConverter.of(blogDO);
  }

  @Override
  public Blog update(BlogReq req) {
    BlogDO entity = new BlogDO();
    entity
        .setId(req.getId())
        .setContent(req.getContent())
        .setDescription(req.getDescription())
        .setTitle(req.getTitle())
        .setType(req.getType());

    if (entity.getId() == null) {
      entity.setCreateTime(LocalDateTime.now());
      blogMapper.insert(entity);
    } else {
      entity.setUpdateTime(LocalDateTime.now());
      blogMapper.updateById(entity);
    }
    return getById(req.getId());
  }

  @Override
  public List<KeyValue> getCount() {
    return blogMapper.getCountByType();
  }
}
