package com.service.service.mapper.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName("link")
public class LinkDO {
  @TableId(type = IdType.AUTO)
  private Long id;

  private String name;

  private String url;

  private LinkStatus status;

  @TableField("`desc`")
  private String desc;

  private LocalDateTime creatTime;

  private LocalDateTime deleteTime;
}
