package com.service.service.service;

import com.service.service.controller.resp.UploadFile;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  /** 上传文件 */
  UploadFile upload(MultipartFile file, String desc, String topic) throws IOException;

  void removeById(Long fileId);
}
