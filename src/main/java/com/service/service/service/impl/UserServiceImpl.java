package com.service.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import static com.service.service.bean.FinalRedisKey.USER_REDIS_KEY;
import com.service.service.controller.resp.UserToken;
import com.service.service.mapper.UserMapper;
import com.service.service.mapper.dao.UserDO;
import com.service.service.service.BaseService;
import com.service.service.service.UserService;
import com.service.service.utils.RedisUtil;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Slf4j
@Service
public class UserServiceImpl extends BaseService implements UserService {

  @Autowired private UserMapper userMapper;

  @Autowired private RedisUtil redisUtil;

  @Override
  @Transactional
  public UserToken login(String username, String password) {
    UserDO userDO = findById(username);

    if (userDO == null) {
      throw new RuntimeException("账户名和密码不匹配");
    }

    password = DigestUtils.md5DigestAsHex(password.getBytes());
    if (!Objects.equals(password, userDO.getPassword())) {
      throw new RuntimeException("账户名和密码不匹配");
    }
    String token = creatorToken(userDO.getId(), username, 36000000);

    // 校验密码
    return new UserToken().setToken(token);
  }

  private UserDO findById(String username) {

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

  private String creatorToken(Long id, String username, long ttlMillis) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    Map<String, Object> claims = new HashMap<>();
    claims.put("uid", id);
    claims.put("user_name", username);
    claims.put("nick_name", username);
    SecretKey key = generalKey();

    JwtBuilder builder =
        Jwts.builder()
            .setClaims(claims)
            .setId(String.valueOf(id))
            .setIssuedAt(now)
            .setSubject(username)
            .signWith(signatureAlgorithm, key);
    if (ttlMillis >= 0) {
      long expMillis = nowMillis + ttlMillis;
      Date exp = new Date(expMillis);
      builder.setExpiration(exp);
    }
    return builder.compact();
  }

  private SecretKey generalKey() {
    String stringKey = "123456";
    byte[] encodedKey = Base64.decodeBase64(stringKey);
    return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
  }
}
