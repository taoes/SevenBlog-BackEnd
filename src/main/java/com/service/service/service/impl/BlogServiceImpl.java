package com.service.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.service.service.bean.FinalRedisKey;
import com.service.service.controller.req.BlogReq;
import com.service.service.controller.resp.Blog;
import com.service.service.controller.resp.PageInfo;
import com.service.service.mapper.BlogMapper;
import com.service.service.mapper.dao.BlogDO;
import com.service.service.mapper.dao.KeyValue;
import com.service.service.service.BlogService;
import com.service.service.service.TagService;
import com.service.service.service.converter.BlogConverter;
import com.service.service.service.converter.JsonConverter;
import com.service.service.utils.RedisUtil;
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

  @Autowired private RedisUtil redisUtil;

  @Override
  public PageInfo<Blog> list(String searchKey, long pageNun, long pageSize, String blogType) {

    Wrapper<BlogDO> queryWrapper =
        new LambdaQueryWrapper<BlogDO>()
            .eq(BlogDO::getDeleted, Boolean.FALSE)
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

    // 检查缓存是否存在
    String blogCacheStr = redisUtil.get(FinalRedisKey.BLOG_REDIS_KEY, key, String.class);
    if (blogCacheStr != null) {
      return JsonConverter.readListValue(blogCacheStr, Blog.class);
    }

    Wrapper<BlogDO> queryWrapper =
        new LambdaQueryWrapper<BlogDO>()
            .eq(StringUtils.hasText(key), BlogDO::getTitle, key)
            .orderByDesc(BlogDO::getId);
    List<Blog> blogList =
        this.blogMapper.selectList(queryWrapper).stream()
            .map(BlogConverter::simpleOf)
            .collect(Collectors.toList());

    blogCacheStr = JsonConverter.toJSONString(blogList);
    redisUtil.set(FinalRedisKey.BLOG_REDIS_KEY, key, blogCacheStr, 600L);
    return blogList;
  }

  @Override
  public Blog getById(Long blogId) {

    // 检查缓存是否存在
    String blogCacheStr = redisUtil.get(FinalRedisKey.BLOG_DETAIL_REDIS_KEY, blogId, String.class);
    if (blogCacheStr != null) {
      return JsonConverter.readJSON(blogCacheStr, Blog.class);
    }

    // 获取文章详情信息
    BlogDO blogDO = blogMapper.selectById(blogId);
    if (blogDO == null) {
      throw new RuntimeException("文章不存在，获取失败");
    }
    // 更新文章个数
    blogDO.setAccessTime(blogDO.getAccessTime() + 1);
    blogMapper.updateById(blogDO);
    Blog blog = BlogConverter.of(blogDO);

    blogCacheStr = JsonConverter.toJSONString(blog);
    redisUtil.set(FinalRedisKey.BLOG_REDIS_KEY, blogId, blogCacheStr, 300L);
    return blog;
  }

  @Override
  public Blog update(BlogReq req) {
    BlogDO entity = new BlogDO();
    entity
        .setId(req.getId())
        .setTags(JsonConverter.toJSONString(req.getTagList()))
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

  @Override
  public List<Blog> hotBlog(int limit) {
    // 检查缓存是否存在
    String blogCacheStr = redisUtil.get(FinalRedisKey.BLOG_HOT_REDIS_KEY, limit, String.class);
    if (blogCacheStr != null) {
      return JsonConverter.readListValue(blogCacheStr, Blog.class);
    }

    Wrapper<BlogDO> queryWrapper =
        new LambdaQueryWrapper<BlogDO>()
            .eq(BlogDO::getDeleted, Boolean.FALSE)
            .orderByDesc(BlogDO::getAccessTime)
            .last("LIMIT " + limit);
    List<Blog> blogList =
        blogMapper.selectList(queryWrapper).stream()
            .map(BlogConverter::simpleOf)
            .collect(Collectors.toList());

    // 检查缓存是否存在
    blogCacheStr = JsonConverter.toJSONString(blogList);
    redisUtil.set(FinalRedisKey.BLOG_HOT_REDIS_KEY, limit, blogCacheStr, 36000L);
    return blogList;
  }

  @Override
  public void remove(Long id) {
    blogMapper.updateById(new BlogDO().setId(id).setDeleted(Boolean.TRUE));
  }
}
