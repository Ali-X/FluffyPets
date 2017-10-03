package com.fluffypets.services;


import com.fluffypets.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category create(Category category);

    Category get(Integer id);
}
