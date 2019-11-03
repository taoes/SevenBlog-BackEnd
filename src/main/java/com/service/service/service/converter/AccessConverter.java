package com.service.service.service.converter;

import com.service.service.controller.resp.Access;
import com.service.service.mapper.dao.AccessDO;

public class AccessConverter {

  public static Access of(AccessDO accessDO) {
    return new Access()
        .setId(accessDO.getId())
        .setIp(accessDO.getIp())
        .setArea(accessDO.getArea())
        .setBrowser(accessDO.getBrowser())
        .setOs(accessDO.getOs())
        .setCreateTime(accessDO.getCreateTime());
  }

  public static AccessDO of(Access access) {
    return new AccessDO()
        .setIp(access.getIp())
        .setArea(access.getArea())
        .setBrowser(access.getBrowser())
        .setOs(access.getOs());
  }
}
