package com.service.service.controller.req;

import lombok.Data;

@Data
public class CommentReq {

  private Long articleId;

  private String name;

  private String site;

  private String email;

  private Integer rate;

  private String content;
}
