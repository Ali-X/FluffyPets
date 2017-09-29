package com.fluffypets.servicies.product;


import com.fluffypets.mvc.model.Category;
import exeptions.DAOException;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category create(Category category);

    Category get(Integer id);

    void close() throws DAOException;
}
