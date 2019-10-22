package com.service.service.mapper.dao;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tag")
public class TagDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String type;

}
