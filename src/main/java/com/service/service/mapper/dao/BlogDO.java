package com.service.service.mapper.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName("blog")
public class BlogDO {

    @TableId(type = IdType.AUTO)
    private long id;

    private String title;

    private String description;

    private String tagIds;

    private String type;

    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
