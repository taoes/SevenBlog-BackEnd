package com.service.service.service;

import com.service.service.controller.resp.Picture;
import com.service.service.mapper.dao.PictureType;
import java.util.List;
import java.util.Map;

public interface PictureService {

  Map<String, List<Picture>> getPictureList(PictureType type);

  void save(String url, String name, String topic, PictureType type);
}
