package com.service.service.mapper.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("repos")
public class ReposDO {

    @TableId(type = IdType.AUTO)
    private long id;

    private String name;

    private String url;

    @TableField("`desc`")
    private String desc;

}
