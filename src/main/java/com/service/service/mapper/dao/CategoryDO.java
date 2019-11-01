package com.service.service.mapper.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("category")
public class CategoryDO {

  @TableId(type = IdType.AUTO)
  private long id;

  private String name;

  @TableField("`key`")
  private String key;

  private String type;

  private String icon;
}
