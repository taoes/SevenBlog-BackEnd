package com.service.service.service.impl;

import com.service.service.controller.resp.UploadFile;
import com.service.service.mapper.dao.PictureType;
import com.service.service.service.FileService;
import com.service.service.service.PictureService;
import com.service.service.utils.QiniuUtils;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

  @Autowired private QiniuUtils qiniuUtil;

  @Autowired private PictureService pictureService;

  @Override
  public UploadFile upload(MultipartFile file) throws IOException {
    String url = qiniuUtil.uploadImg(file.getInputStream());
    log.info("文件上传完成:{}", url);
    // 写入数据库
    pictureService.save(url, null, null, PictureType.BLOG_PIC);
    return new UploadFile().setUrl(url + "?imageView2/2/w/800");
  }
}
