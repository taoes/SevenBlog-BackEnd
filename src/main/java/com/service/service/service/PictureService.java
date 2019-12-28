package com.service.service.service;

import com.service.service.controller.resp.PageInfo;
import com.service.service.controller.resp.Picture;
import com.service.service.mapper.dao.PictureType;
import java.util.List;
import java.util.Map;

public interface PictureService {

  Map<String, List<Picture>> getPictureList(PictureType type);

  void save(String url, String name, String desc, String topic, PictureType type);

  /** 获取所有图片信息 */
  Map<String, List<Picture>> all();

  PageInfo<Picture> page(long pageNumber, long pageSize);

  void remove(Long fileId);

  void update(Picture picture);
}
