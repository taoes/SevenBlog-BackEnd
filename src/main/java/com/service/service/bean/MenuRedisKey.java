package com.service.service.bean;

public class MenuRedisKey extends RedisKey {
  @Override
  public String getPrefix() {
    return "MENU";
  }

}
