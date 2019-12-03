package com.service.service.controller.resp;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Link {

  private Long id;

  private String name;

  private String url;

  private String desc;

  private String creatTime;
}
