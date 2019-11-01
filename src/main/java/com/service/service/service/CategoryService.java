package com.service.service.service;

import com.service.service.controller.resp.Category;
import java.util.List;

public interface CategoryService {

  List<Category> all();

  Category update(Category category);

  List<Category> list();
}
