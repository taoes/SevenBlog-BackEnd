package com.service.service.service.converter;

import com.service.service.controller.resp.Blog;
import com.service.service.controller.resp.Tag;
import com.service.service.mapper.dao.BlogDO;
import com.service.service.mapper.dao.TagDO;

public class TagConverter implements Converter {

    public static Tag of(TagDO tagDO) {
        return new Tag()
                .setId(tagDO.getId())
                .setName(tagDO.getName())
                .setType(tagDO.getType());
    }
}
