package com.service.service.service;

import com.service.service.controller.resp.Repos;
import java.util.List;

public interface ReposService {

    List<Repos> getAllList(int size);
}
