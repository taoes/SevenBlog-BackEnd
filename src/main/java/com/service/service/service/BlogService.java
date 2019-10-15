package com.service.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.service.service.controller.resp.Blog;

public interface BlogService {

    /**
     * 查询博文列表
     */
    Page<Blog> list(String key, int pageNun, int pageSize);
}
