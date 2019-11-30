package com.service.service.bean;

import java.io.Serializable;

public abstract class RedisKey {

  public abstract String getPrefix();

  public String getRedisKey(Serializable id) {
    if (id == null) {
      id = "EMPTY_KEY";
    }
    return getPrefix() + ":" + String.valueOf(id);
  }
}
