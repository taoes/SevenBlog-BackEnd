package com.service.service.bean;

public class MenuRedisKey implements RedisKey {
  @Override
  public String getPrefix() {
    return "MENU";
  }
}
