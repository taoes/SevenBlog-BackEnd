package com.service.service.service;

import com.service.service.controller.resp.Access;
import com.service.service.mapper.dao.KeyValue;
import java.util.List;

public interface AccessService {

  void add(Access req);

  List<KeyValue> list();
}
