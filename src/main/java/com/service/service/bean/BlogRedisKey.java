package com.service.service.bean;

public class BlogRedisKey implements RedisKey {
  @Override
  public String getPrefix() {
    return "BLOG";
  }

}
