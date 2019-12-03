package com.service.service.service;

import com.service.service.controller.req.BlogReq;
import com.service.service.controller.resp.Blog;
import com.service.service.controller.resp.PageInfo;
import com.service.service.mapper.dao.KeyValue;
import java.util.List;

public interface BlogService {

  /** 查询博文列表 */
  PageInfo<Blog> list(String key, long pageNun, long pageSize, String blogType);

  /**
   * 获取单个文章的内容详情信息
   *
   * @param blogId
   * @return
   */
  Blog getById(Long blogId);

  /** 更新博客 */
  Blog update(BlogReq req);

  /** 搜索所有文章的概要信息 */
  List<Blog> listAll(String key);

  /** 获取文章统计 */
  List<KeyValue> getCount();

  /** 获取最火的文章 */
  List<Blog> hotBlog(int limit);
}
