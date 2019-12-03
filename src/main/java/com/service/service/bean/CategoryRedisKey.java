package com.service.service.bean;

public class CategoryRedisKey implements RedisKey {
  @Override
  public String getPrefix() {
    return "CATEGORY";
  }
}
