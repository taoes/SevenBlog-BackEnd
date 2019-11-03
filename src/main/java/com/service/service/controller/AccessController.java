package com.service.service.controller;

import com.service.service.controller.resp.Access;
import com.service.service.mapper.dao.KeyValue;
import com.service.service.service.AccessService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/access")
public class AccessController {

  @Autowired private AccessService accessService;

  @GetMapping("/count")
  public List<KeyValue> getAllCount() {
    return accessService.list();
  }

  @PostMapping
  public void add(@RequestBody Access req) {
    accessService.add(req);
  }
}
