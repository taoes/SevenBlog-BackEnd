package com.service.service.bean;

import com.service.service.mapper.dao.UserDO;

public class UserRedisKey extends RedisKey {
  @Override
  public String getPrefix() {
    return "USER";
  }

}
