package com.service.service.bean;

import com.service.service.mapper.dao.UserDO;

public class UserRedisKey implements RedisKey {
  @Override
  public String getPrefix() {
    return "USER";
  }

}
