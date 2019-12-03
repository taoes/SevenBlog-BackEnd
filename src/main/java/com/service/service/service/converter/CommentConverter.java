package com.service.service.service.converter;

import com.service.service.controller.resp.Comment;
import com.service.service.mapper.dao.CommentDO;
import java.time.format.DateTimeFormatter;

public class CommentConverter implements Converter {

  public static Comment of(CommentDO commentDO) {
    return commentDO == null
        ? null
        : new Comment()
            .setId(commentDO.getId())
            .setName(commentDO.getName())
            .setSite(commentDO.getSite())
            .setContent(commentDO.getContent())
            .setEmail(commentDO.getEmail())
            .setRate(commentDO.getRate())
            .setCreateDatetime(commentDO.getCreateDatetime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
  }
}
