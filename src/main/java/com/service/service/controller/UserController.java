package com.service.service.controller;

import com.service.service.controller.req.LoginInfo;
import com.service.service.controller.resp.UserToken;
import com.service.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired private UserService userService;

  @PostMapping("/login")
  public UserToken login(@RequestBody LoginInfo info) {
    return userService.login(info.getUsername(), info.getPassword());
  }
}
