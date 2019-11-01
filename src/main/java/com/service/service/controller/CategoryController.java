package com.service.service.controller;

import com.service.service.controller.resp.Category;
import com.service.service.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired private CategoryService categoryService;

  @GetMapping("/list")
  public List<Category> list() {
    return categoryService.list();
  }

  @GetMapping
  public List<Category> all() {
    return categoryService.all();
  }

  @PostMapping
  public Category update(@RequestBody Category category) {
    return categoryService.update(category);
  }
}
