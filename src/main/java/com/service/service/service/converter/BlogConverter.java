package com.service.service.service.converter;

import com.service.service.controller.resp.Blog;
import com.service.service.mapper.dao.BlogDO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlogConverter implements Converter {

  public static Blog of(BlogDO blogDO) {

    List<String> tagList =
        Optional.ofNullable(JsonConverter.readListValue(blogDO.getTags(), String.class))
            .orElse(new ArrayList<>(0));

    return new Blog()
        .setId(blogDO.getId())
        .setAccess(blogDO.getAccessTime())
        .setTagList(tagList)
        .setTitle(blogDO.getTitle())
        .setType(blogDO.getType())
        .setContent(blogDO.getContent())
        .setDescription(blogDO.getDescription())
        .setCreateDatetime(blogDO.getCreateTime())
        .setUpdateDatetime(blogDO.getUpdateTime());
  }

  public static Blog simpleOf(BlogDO blogDO) {

    List<String> tagList =
        Optional.ofNullable(JsonConverter.readListValue(blogDO.getTags(), String.class))
            .orElse(new ArrayList<>(0));
    return new Blog()
        .setId(blogDO.getId())
        .setAccess(blogDO.getAccessTime())
        .setTagList(tagList)
        .setTitle(blogDO.getTitle())
        .setType(blogDO.getType())
        .setDescription(blogDO.getDescription())
        .setCreateDatetime(blogDO.getCreateTime())
        .setUpdateDatetime(blogDO.getUpdateTime());
  }
}
