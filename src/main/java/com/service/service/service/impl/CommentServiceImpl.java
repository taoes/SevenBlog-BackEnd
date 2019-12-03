package com.service.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.service.service.bean.FinalRedisKey;
import com.service.service.controller.req.CommentReq;
import com.service.service.controller.resp.Comment;
import com.service.service.mapper.CommentMapper;
import com.service.service.mapper.dao.CommentDO;
import com.service.service.service.CommentService;
import com.service.service.service.converter.CommentConverter;
import com.service.service.service.converter.JsonConverter;
import com.service.service.utils.RedisUtil;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired private CommentMapper commentMapper;

  @Autowired private RedisUtil redisUtil;

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

  @Override
  public List<Comment> latest(int limit) {
    String latestCommentStr = redisUtil.get(FinalRedisKey.COMMENT_LATEST_KEY, limit, String.class);
    if (StringUtils.hasText(latestCommentStr)) {
      return JsonConverter.readListValue(latestCommentStr, Comment.class);
    }
    Wrapper<CommentDO> queryWrapper =
        new LambdaQueryWrapper<CommentDO>().orderByDesc(CommentDO::getId).last("LIMIT " + limit);
    List<Comment> latestComment =
        this.commentMapper.selectList(queryWrapper).stream()
            .map(CommentConverter::of)
            .collect(Collectors.toList());
    latestCommentStr = JsonConverter.toJSONString(latestComment);
    redisUtil.set(FinalRedisKey.COMMENT_LATEST_KEY, limit, latestCommentStr, 60L);
    return latestComment;
  }

  private Comment findById(Serializable id) {
    return CommentConverter.of(this.commentMapper.selectById(id));
  }
}
