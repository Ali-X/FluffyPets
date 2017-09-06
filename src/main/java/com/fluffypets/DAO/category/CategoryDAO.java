package com.fluffypets.DAO.category;


import com.fluffypets.MVC.model.Category;
import com.fluffypets.DAO.*;

import java.util.List;

public interface CategoryDAO extends GenericDAO<Category> {

    List<Category> getAll();

}
