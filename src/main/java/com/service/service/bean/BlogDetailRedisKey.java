package com.service.service.bean;

public class BlogDetailRedisKey implements RedisKey {
  @Override
  public String getPrefix() {
    return "BLOG_DETAIL";
  }

}
