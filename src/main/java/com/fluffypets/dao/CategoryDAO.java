package com.fluffypets.dao;


import com.fluffypets.entities.Category;

import java.util.List;

public interface CategoryDAO extends GenericDAO<Category> {

    List<Category> getAll();

}
