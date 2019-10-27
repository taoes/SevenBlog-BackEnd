package com.service.service.service;

import com.service.service.controller.resp.Category;
import java.util.List;
import java.util.Map;

public interface CategoryService {

  Map<String, List<Category>> all();

  Category update(Category category);

  List<Category> list();
}
