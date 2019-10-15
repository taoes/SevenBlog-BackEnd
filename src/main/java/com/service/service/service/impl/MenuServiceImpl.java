package com.service.service.service.impl;

import com.service.service.controller.resp.Menu;
import com.service.service.mapper.MenuMapper;
import com.service.service.mapper.dao.MenuDO;
import com.service.service.service.MenuService;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuMapper menuMapper;


    @Override
    public Collection<Menu> getAllMenu() {
        List<MenuDO> menuDOS = menuMapper.selectList(null);
        return menuDOS.stream().map(Menu::of).collect(Collectors.toList());
    }
}
