package com.fluffypets.DAO;


import com.fluffypets.MVC.model.Category;

import java.util.List;

public interface CategoryDao extends GenericDao<Category> {

    List<Category> getAll();

    List<Category> findAll();
}
