package com.service.service.controller.req;

import java.util.List;
import lombok.Data;

@Data
public class BlogReq {

  private Long id;

  private String title;

  private String type;

  private String description;

  private List<String> tagList;

  private String content;
}
