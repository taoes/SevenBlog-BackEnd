package com.service.service.controller.resp;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UploadFile {

  private Long id;

  private String url;

  private String name;

  private LocalDateTime createDatetime;
}
