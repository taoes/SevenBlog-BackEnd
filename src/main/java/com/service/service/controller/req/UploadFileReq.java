package com.service.service.controller.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadFileReq {

  private String desc;

  private String topic;

  private MultipartFile file;
}
