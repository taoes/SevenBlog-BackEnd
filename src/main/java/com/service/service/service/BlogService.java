package com.service.service.service;

import com.service.service.controller.resp.Blog;
import com.service.service.controller.resp.PageInfo;

public interface BlogService {

    /**
     * 查询博文列表
     */
    PageInfo<Blog> list(String key, long pageNun, long pageSize, String blogType);

    /**
     * 获取单个文章的内容详情信息
     *
     * @param blogId
     * @return
     */
    Blog getById(Long blogId);
}
