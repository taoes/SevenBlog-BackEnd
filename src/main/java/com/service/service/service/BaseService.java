package com.service.service.service;

import com.service.service.mapper.LinkMapper;
import com.service.service.mapper.UserMapper;
import com.service.service.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

  @Autowired protected UserMapper userMapper;

  @Autowired protected LinkMapper linkMapper;

  @Autowired protected RedisUtil redisUtil;
}
