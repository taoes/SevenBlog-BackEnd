package com.service.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.service.service.controller.req.CommentReq;
import com.service.service.controller.resp.Comment;
import com.service.service.mapper.CommentMapper;
import com.service.service.mapper.dao.CommentDO;
import com.service.service.service.CommentService;
import com.service.service.service.converter.CommentConverter;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired private CommentMapper commentMapper;

  @Override
  public Comment add(CommentReq req) {
    CommentDO entity = new CommentDO();
    entity
        .setName(req.getName())
        .setArticleId(req.getArticleId())
        .setContent(req.getContent())
        .setEmail(req.getEmail())
        .setRate(req.getRate())
        .setSite(req.getSite());
    commentMapper.insert(entity);
    return findById(entity.getId());
  }

  @Override
  public List<Comment> list(Long articleId) {
    Wrapper<CommentDO> queryWrapper =
        new LambdaQueryWrapper<CommentDO>()
            .eq(CommentDO::getArticleId, articleId)
            .orderByDesc(CommentDO::getId);
    return this.commentMapper.selectList(queryWrapper).stream()
        .map(CommentConverter::of)
        .collect(Collectors.toList());
  }

  private Comment findById(Serializable id) {
    return CommentConverter.of(this.commentMapper.selectById(id));
  }
}
