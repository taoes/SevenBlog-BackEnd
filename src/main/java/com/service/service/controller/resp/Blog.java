package com.service.service.controller.resp;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class Blog {

  private long id;

  private int access;

  private String title;

  private String type;

  private String description;

  private String content;

  private List<String> tagList;

  private LocalDateTime createDatetime;

  private LocalDateTime updateDatetime;
}
