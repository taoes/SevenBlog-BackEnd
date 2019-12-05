package com.service.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.service.service.mapper.dao.AccessDO;
import com.service.service.mapper.dao.KeyValue;
import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface AccessMapper extends BaseMapper<AccessDO> {

  @Select({
    "SELECT access.area AS name, COUNT(1) AS value",
    "FROM access",
    "GROUP BY access.area ",
    "ORDER BY COUNT(1) DESC"
  })
  List<KeyValue> count();
}
