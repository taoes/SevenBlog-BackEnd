package com.service.service.service;

import com.service.service.controller.resp.Tag;
import java.util.Collection;
import java.util.List;

public interface TagService {

    List<Tag> all();

    /**
     * 查询给定的所有标签
     */
    List<Tag> getByIds(Collection<Long> ids);

    /**
     * 获取标签详情
     */
    Tag getById(Long id);

    /**
     * 新增标签
     *
     * @return
     */
    Tag add(String name, String type);

    /**
     * 更新标签
     *
     * @param tag
     * @return
     */
    Tag update(Tag tag);
}
