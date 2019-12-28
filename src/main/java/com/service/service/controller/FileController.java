package com.service.service.controller;

import com.service.service.controller.req.UploadFileReq;
import com.service.service.controller.resp.UploadFile;
import com.service.service.service.FileService;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

  @Autowired private FileService fileService;

  @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public UploadFile uploadFile(@ModelAttribute UploadFileReq file) {
    try {
      return fileService.upload(file.getFile(), file.getDesc(), file.getTopic());
    } catch (IOException e) {
      log.error("文件上传失败", e);
      throw new RuntimeException(e);
    }
  }

  @DeleteMapping("/{fileId}")
  public void deleteFile(@PathVariable("fileId") Long fileId){
    fileService.removeById(fileId);
  }
}
