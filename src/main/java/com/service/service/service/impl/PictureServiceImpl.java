package com.service.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.service.service.controller.resp.Picture;
import com.service.service.mapper.PictureMapper;
import com.service.service.mapper.dao.PictureDO;
import com.service.service.mapper.dao.PictureType;
import com.service.service.service.PictureService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

  @Autowired PictureMapper pictureMapper;

  @Override
  public Map<String, List<Picture>> getPictureList(PictureType type) {
    Wrapper<PictureDO> queryWrapper =
        new LambdaQueryWrapper<PictureDO>().eq(PictureDO::getType, type.name());
    List<PictureDO> pictureDOList = pictureMapper.selectList(queryWrapper);
    return pictureDOList.stream()
        .map(Picture::of)
        .collect(Collectors.groupingBy(Picture::getTopic));
  }
}
