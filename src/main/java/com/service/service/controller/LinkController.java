package com.service.service.controller;

import com.service.service.controller.resp.Link;
import com.service.service.service.LinkService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkController {

  @Autowired private LinkService linkService;

  @PostMapping
  public Link create() {
    return linkService.add(null, null, null);
  }

  @GetMapping
  public List<Link> all() {
    return linkService.getAll();
  }
}
