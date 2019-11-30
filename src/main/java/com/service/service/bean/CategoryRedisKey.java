package com.service.service.bean;

public class CategoryRedisKey extends RedisKey {
  @Override
  public String getPrefix() {
    return "CATEGORY";
  }
}
