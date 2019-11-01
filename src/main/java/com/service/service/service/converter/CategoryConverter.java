package com.service.service.service.converter;

import com.service.service.controller.resp.Category;
import com.service.service.mapper.dao.CategoryDO;

public class CategoryConverter implements Converter {

  public static Category of(CategoryDO categoryDO) {
    if (categoryDO == null) {
      return null;
    }
    return new Category()
        .setId(categoryDO.getId())
        .setName(categoryDO.getName())
        .setKey(categoryDO.getKey())
        .setType(categoryDO.getType())
        .setIcon(categoryDO.getIcon());
  }

  public static CategoryDO of(Category category) {
    if (category == null) {
      return null;
    }
    return new CategoryDO()
        .setId(category.getId())
        .setName(category.getName())
        .setKey(category.getKey())
        .setType(category.getType())
        .setIcon(category.getIcon());
  }
}
