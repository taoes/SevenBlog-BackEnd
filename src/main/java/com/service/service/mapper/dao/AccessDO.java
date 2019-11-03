package com.service.service.mapper.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName("access")
public class AccessDO {

  @TableId(type = IdType.AUTO)
  private Long id;

  private String area;

  private String browser;

  private String ip;

  private String os;

  private LocalDateTime createTime;
}
