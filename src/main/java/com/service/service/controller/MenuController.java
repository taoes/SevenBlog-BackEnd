package com.service.service.controller;


import com.service.service.controller.resp.Menu;
import com.service.service.service.MenuService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public Collection<Menu> getAllMenuList() {
        return menuService.getAllMenu();
    }

}
