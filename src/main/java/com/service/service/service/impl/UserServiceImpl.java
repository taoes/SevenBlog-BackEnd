package com.service.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import static com.service.service.bean.FinalRedisKey.USER_REDIS_KEY;
import com.service.service.controller.resp.UserToken;
import com.service.service.mapper.UserMapper;
import com.service.service.mapper.dao.UserDO;
import com.service.service.service.BaseService;
import com.service.service.service.UserService;
import com.service.service.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl extends BaseService implements UserService {

  @Autowired private UserMapper userMapper;

  @Autowired private RedisUtil redisUtil;

  @Override
  @Transactional
  public UserToken login(String username, String password) {
    UserDO userDO = findById(username);
    // 校验密码
    return null;
  }

  public UserDO findById(String username) {

    UserDO aDo = redisUtil.get(USER_REDIS_KEY, username, UserDO.class);
    if (aDo != null) {
      return aDo;
    }

    UserDO userDO =
        userMapper.selectOne(new LambdaQueryWrapper<UserDO>().eq(UserDO::getUsername, username));
    if (userDO != null) {
      redisUtil.set(USER_REDIS_KEY, username, userDO, 600L);
    }

    return userDO;
  }
}
