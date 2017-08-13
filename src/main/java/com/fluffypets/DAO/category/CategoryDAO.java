package com.fluffypets.DAO.category;

import com.fluffypets.DAO.GenericDAO;
import com.fluffypets.MVC.model.Category;

import java.util.List;

public interface CategoryDAO extends GenericDAO<Category> {

    List<Category> getAll();

}
