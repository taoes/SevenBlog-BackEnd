package com.service.service.service;

import com.service.service.controller.resp.UploadFile;
import java.io.IOException;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  /** 上传文件 */
  UploadFile upload(MultipartFile file) throws IOException;
}
