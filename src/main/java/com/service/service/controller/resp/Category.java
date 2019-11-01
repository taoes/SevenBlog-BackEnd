package com.service.service.controller.resp;

import java.util.List;
import lombok.Data;

@Data
public class Category {

  private Long id;

  private String name;

  private String key;

  private String type;

  private String icon;

  private List<Category> sub;
}
