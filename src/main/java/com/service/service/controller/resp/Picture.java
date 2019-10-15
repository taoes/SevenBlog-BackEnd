package com.service.service.controller.resp;

import com.service.service.mapper.dao.PictureDO;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Picture {

    private Long id;

    private String name;

    private String description;

    private String img;

    private String topic;

    private Date createTime;

    private Date updateTime;

    public static Picture of(PictureDO pictureDO) {
        return new Picture()
                .setId(pictureDO.getId())
                .setName(pictureDO.getName())
                .setDescription(pictureDO.getDescription())
                .setImg(pictureDO.getImg())
                .setTopic(pictureDO.getTopic())
                .setCreateTime(pictureDO.getCreateTime())
                .setUpdateTime(pictureDO.getUpdateTime());
    }
}