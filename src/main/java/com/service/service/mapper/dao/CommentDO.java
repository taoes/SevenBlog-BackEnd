package com.service.service.mapper.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName("comment")
public class CommentDO {

  @TableId(type = IdType.AUTO)
  private Long id;

  private Long articleId;

  private String name;

  private String site;

  private String email;

  private Integer rate;

  private String content;

  private LocalDateTime createDatetime;
}
