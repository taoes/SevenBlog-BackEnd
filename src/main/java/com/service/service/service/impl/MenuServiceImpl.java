package com.service.service.service.impl;

import com.service.service.bean.FinalRedisKey;
import com.service.service.controller.resp.Menu;
import com.service.service.mapper.MenuMapper;
import com.service.service.mapper.dao.MenuDO;
import com.service.service.service.MenuService;
import com.service.service.service.converter.JsonConverter;
import com.service.service.utils.RedisUtil;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

  @Autowired private MenuMapper menuMapper;

  @Autowired RedisUtil redisUtil;

  @Override
  public Collection<Menu> getAllMenu() {

    String blogCacheStr = redisUtil.get(FinalRedisKey.MENU_REDIS_KEY, null, String.class);
    if (blogCacheStr != null) {
      return JsonConverter.readListValue(blogCacheStr, Menu.class);
    }

    List<MenuDO> menuDOS = menuMapper.selectList(null);
    List<Menu> menuList = menuDOS.stream().map(Menu::of).collect(Collectors.toList());

    blogCacheStr = JsonConverter.toJSONString(menuList);
    redisUtil.set(FinalRedisKey.MENU_REDIS_KEY, null, blogCacheStr, 600L);
    return menuList;
  }
}
