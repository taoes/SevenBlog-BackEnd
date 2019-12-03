package com.service.service.controller.resp;

import lombok.Data;

@Data
public class Comment {

  private Long id;

  private String name;

  private String site;

  private String email;

  private Integer rate;

  private String content;

  private String createDatetime;
}
