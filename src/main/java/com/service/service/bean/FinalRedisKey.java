package com.service.service.bean;

public interface FinalRedisKey {
  RedisKey USER_REDIS_KEY = () -> "USERl";

  RedisKey CATEGORY_REDIS_KEY = () -> "CATEGORY";

  RedisKey BLOG_REDIS_KEY = () -> "BLOG";

  RedisKey BLOG_DETAIL_REDIS_KEY = () -> "BLOG_DETAIL";

  RedisKey BLOG_HOT_REDIS_KEY = () -> "BLOG_REDIS";

  RedisKey MENU_REDIS_KEY = () -> "MENU";

  RedisKey COMMENT_LATEST_KEY = () -> "COMMENT_LATEST";

  RedisKey LINK_REDIS_KEY = () -> "LINK";
}
