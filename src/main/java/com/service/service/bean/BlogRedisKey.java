package com.service.service.bean;

public class BlogRedisKey extends RedisKey {
  @Override
  public String getPrefix() {
    return "BLOG";
  }

}
