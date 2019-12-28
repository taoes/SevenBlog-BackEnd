package com.service.service.controller;

import com.service.service.controller.req.PictureUpdateReq;
import com.service.service.controller.resp.PageInfo;
import com.service.service.controller.resp.Picture;
import com.service.service.mapper.dao.PictureType;
import com.service.service.service.PictureService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/picture")
public class PictureController {

  @Autowired private PictureService pictureService;

  @GetMapping("/type/{type}")
  @ApiOperation("获取某类图片")
  public Map<String, List<Picture>> getPicture(@PathVariable("type") PictureType type) {
    return pictureService.getPictureList(type);
  }

  @GetMapping
  @ApiOperation("获取所有图片")
  public Map<String, List<Picture>> getAllPicture() {
    return pictureService.all();
  }

  @GetMapping("/page")
  @ApiOperation("获取所有图片")
  public PageInfo<Picture> getPicByPage(
      @RequestParam(defaultValue = "1") long pageNumber,
      @RequestParam(defaultValue = "10") long pageSize) {
    return pictureService.page(pageNumber, pageSize);
  }

  @PatchMapping("/{fileId}")
  @ApiOperation("更新图片信息")
  public void updateById(@PathVariable("fileId") Long fileId, @RequestBody PictureUpdateReq req) {
    pictureService.update(
        new Picture()
            .setId(fileId)
            .setName(req.getName())
            .setDescription(req.getDesc())
            .setTopic(req.getTopic()));
  }
}
