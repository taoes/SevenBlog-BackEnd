package com.service.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.service.service.mapper.dao.BlogDO;
import com.service.service.mapper.dao.KeyValue;
import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface BlogMapper extends BaseMapper<BlogDO> {

  @Select({
    "SELECT category.name AS name, COUNT(1) AS value",
    "FROM blog",
    "INNER JOIN category ON blog.type = category.`key`",
    "GROUP BY category.name",
  })
  List<KeyValue> getCountByType();
}
