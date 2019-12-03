package com.service.service.bean;

public class CommentLatestRedisKey extends RedisKey {
  @Override
  public String getPrefix() {
    return "COMMENT_LATEST";
  }
}
