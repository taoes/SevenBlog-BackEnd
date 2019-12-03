package com.service.service.mapper.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LinkStatus {
  NORMAL("正常"),
  DISABLE("禁用"),
  DELETE("删除");

  @Getter private String desc;
}
