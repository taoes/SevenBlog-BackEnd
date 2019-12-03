package com.service.service.service.converter;

import com.service.service.controller.resp.Link;
import com.service.service.mapper.dao.LinkDO;
import com.service.service.utils.TimeUtils;

public class LinKConverter {
  public static Link of(LinkDO linkDO) {

    return new Link()
        .setId(linkDO.getId())
        .setName(linkDO.getName())
        .setUrl(linkDO.getUrl())
        .setCreatTime(linkDO.getCreatTime().format(TimeUtils.formatter));
  }
}
