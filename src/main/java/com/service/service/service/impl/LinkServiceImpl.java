package com.service.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.service.service.bean.FinalRedisKey;
import com.service.service.controller.resp.Link;
import com.service.service.mapper.dao.LinkDO;
import com.service.service.mapper.dao.LinkStatus;
import com.service.service.service.BaseService;
import com.service.service.service.LinkService;
import com.service.service.service.converter.JsonConverter;
import com.service.service.service.converter.LinKConverter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LinkServiceImpl extends BaseService implements LinkService {

  @Override
  public List<Link> getAll() {
    String linkCache = redisUtil.get(FinalRedisKey.LINK_REDIS_KEY, null, String.class);
    if (StringUtils.hasText(linkCache)) {
      return JsonConverter.readListValue(linkCache, Link.class);
    }

    Wrapper<LinkDO> queryWrapper =
        new LambdaQueryWrapper<LinkDO>()
            .eq(LinkDO::getStatus, LinkStatus.NORMAL)
            .isNull(LinkDO::getDeleteTime);
    List<Link> links =
        linkMapper.selectList(queryWrapper).stream()
            .map(LinKConverter::of)
            .collect(Collectors.toList());
    linkCache = JsonConverter.toJSONString(links);
    redisUtil.set(FinalRedisKey.LINK_REDIS_KEY, null, linkCache, 3600L);
    return links;
  }

  @Override
  public Link add(String name, String url, String desc) {
    throw new RuntimeException("暂不支持友情连接申请");
  }
}
