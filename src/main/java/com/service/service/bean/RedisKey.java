package com.service.service.bean;

import java.io.Serializable;

public interface RedisKey {

  String getPrefix();

  default String getRedisKey(Serializable id) {
    if (id == null) {
      id = "EMPTY_KEY";
    }
    return getPrefix() + ":" + String.valueOf(id);
  }
}
