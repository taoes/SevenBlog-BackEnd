package com.service.service.service.converter;

import com.service.service.controller.resp.Blog;
import com.service.service.mapper.dao.BlogDO;

public class BlogConverter implements Converter {

  public static Blog of(BlogDO blogDO) {
    return new Blog()
        .setId(blogDO.getId())
        .setTitle(blogDO.getTitle())
        .setType(blogDO.getType())
        .setContent(blogDO.getContent())
        .setDescription(blogDO.getDescription())
        .setCreateDatetime(blogDO.getCreateTime())
        .setUpdateDatetime(blogDO.getUpdateTime());
  }

  public static Blog simpleOf(BlogDO blogDO) {
    return new Blog()
        .setId(blogDO.getId())
        .setTitle(blogDO.getTitle())
        .setType(blogDO.getType())
        .setDescription(blogDO.getDescription())
        .setCreateDatetime(blogDO.getCreateTime())
        .setUpdateDatetime(blogDO.getUpdateTime());
  }
}
