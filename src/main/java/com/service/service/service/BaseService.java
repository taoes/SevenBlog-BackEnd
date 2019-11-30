package com.service.service.service;

import com.service.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

  @Autowired protected UserMapper userMapper;
}
