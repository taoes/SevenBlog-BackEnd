package com.service.service.service.impl;

import com.service.service.controller.resp.Access;
import com.service.service.mapper.AccessMapper;
import com.service.service.mapper.dao.KeyValue;
import com.service.service.service.AccessService;
import com.service.service.service.converter.AccessConverter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccessServiceImpl implements AccessService {

  @Autowired private AccessMapper accessMapper;

  @Override
  public void add(Access req) {
    accessMapper.insert(AccessConverter.of(req));
  }

  @Override
  public List<KeyValue> list() {
    return accessMapper.count();
  }
}
