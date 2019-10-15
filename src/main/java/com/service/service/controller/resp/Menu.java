package com.service.service.controller.resp;

import com.service.service.mapper.dao.MenuDO;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class Menu {


    private Long id;

    private String name;

    private String path;

    private String icon;


    public static Menu of(MenuDO menuDO) {
        return new Menu().setId(menuDO.getId())
                .setName(menuDO.getName())
                .setPath(menuDO.getPath())
                .setIcon(menuDO.getIcon());
    }
}
