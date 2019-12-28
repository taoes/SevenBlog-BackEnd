package com.service.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.service.service.controller.resp.PageInfo;
import com.service.service.controller.resp.Picture;
import com.service.service.mapper.PictureMapper;
import com.service.service.mapper.dao.PictureDO;
import com.service.service.mapper.dao.PictureType;
import com.service.service.service.PictureService;
import java.util.Collection;
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

  @Override
  public void save(String url, String name, String desc, String topic, PictureType type) {
    PictureDO entity = new PictureDO();
    entity.setImg(url).setName(name).setTopic(topic).setType(type).setDescription(desc);
    this.pictureMapper.insert(entity);
  }

  @Override
  public Map<String, List<Picture>> all() {
    Wrapper<PictureDO> queryWrapper =
        new LambdaQueryWrapper<PictureDO>().orderByDesc(PictureDO::getId);
    List<PictureDO> pictureDOList = pictureMapper.selectList(queryWrapper);
    return pictureDOList.stream()
        .map(Picture::of)
        .filter(picture -> picture.getTopic() != null)
        .collect(Collectors.groupingBy(Picture::getTopic));
  }

  @Override
  public PageInfo<Picture> page(long pageNumber, long pageSize) {
    Wrapper<PictureDO> queryWrapper =
        new LambdaQueryWrapper<PictureDO>().orderByDesc(PictureDO::getId);
    IPage<PictureDO> pictureDoPage =
        pictureMapper.selectPage(new Page<>(pageNumber, pageSize), queryWrapper);

    List<PictureDO> records = pictureDoPage.getRecords();

    Collection<Picture> data = records.stream().map(Picture::of).collect(Collectors.toList());

    return new PageInfo<Picture>()
        .setData(data)
        .setTotal(pictureDoPage.getTotal())
        .setPage(pictureDoPage.getPages());
  }

  @Override
  public void remove(Long fileId) {
    pictureMapper.deleteById(fileId);
  }

  @Override
  public void update(Picture picture) {
    pictureMapper.updateById(
        new PictureDO()
            .setId(picture.getId())
            .setDescription(picture.getDescription())
            .setTopic(picture.getTopic()));
  }
}
