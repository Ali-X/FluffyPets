package com.fluffypets.servicies;

import com.fluffypets.MVC.model.Category;

import java.util.List;

public interface CategoryService {

  Category update(Category category);
  List<Category> findAll();

  Category create(Category category);
}
