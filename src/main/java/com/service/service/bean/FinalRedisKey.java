package com.service.service.bean;

public interface FinalRedisKey {
  RedisKey USER_REDIS_KEY = new UserRedisKey();

  RedisKey CATEGORY_REDIS_KEY = new CategoryRedisKey();

  RedisKey BLOG_REDIS_KEY = new BlogRedisKey();

  RedisKey BLOG_DETAIL_REDIS_KEY = new BlogDetailRedisKey();


  RedisKey MENU_REDIS_KEY = new MenuRedisKey();
}
