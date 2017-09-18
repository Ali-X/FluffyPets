package com.fluffypets.servicies;


import com.fluffypets.MVC.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category create(Category category);

    Category get(Integer id);

    void close() throws Exception;
}
