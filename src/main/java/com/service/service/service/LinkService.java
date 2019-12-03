package com.service.service.service;

import com.service.service.controller.resp.Link;
import java.util.List;

public interface LinkService {

  List<Link> getAll();

  Link add(String name, String url, String desc);
}
