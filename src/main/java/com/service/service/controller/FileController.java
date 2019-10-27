package com.service.service.controller;

import com.service.service.controller.resp.UploadFile;
import com.service.service.service.FileService;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

  @Autowired private FileService fileService;

  @PostMapping("/upload")
  public UploadFile uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      return fileService.upload(file);
    } catch (IOException e) {
      log.error("文件上传失败", e);
      throw new RuntimeException(e);
    }
  }
}
