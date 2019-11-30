package com.service.service.bean;

public class BlogDetailRedisKey extends RedisKey {
  @Override
  public String getPrefix() {
    return "BLOG_DETAIL";
  }

}
