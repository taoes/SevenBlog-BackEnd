package com.service.service.controller.resp;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Blog {

  private long id;

  private String title;

  private String type;

  private String description;

  private String content;

  private LocalDateTime createDatetime;

  private LocalDateTime updateDatetime;
}
