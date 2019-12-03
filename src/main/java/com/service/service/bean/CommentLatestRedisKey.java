package com.service.service.bean;

public class CommentLatestRedisKey implements RedisKey {
  @Override
  public String getPrefix() {
    return "COMMENT_LATEST";
  }
}
