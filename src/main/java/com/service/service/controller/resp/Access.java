package com.service.service.controller.resp;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Access {

  private Long id;

  private String area;

  private String browser;

  private String ip;

  private String os;

  private LocalDateTime createTime;
}
