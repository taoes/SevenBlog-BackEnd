package com.service.service.service;

import com.service.service.controller.resp.UserToken;

public interface UserService {

  UserToken login(String username, String password);
}
