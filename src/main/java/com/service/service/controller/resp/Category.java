package com.service.service.controller.resp;

import lombok.Data;

@Data
public class Blog {

  private long id;

  private String title;

  private String type;

  private String description;

  private String content;
}
