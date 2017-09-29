package com.fluffypets.dao.category;


import com.fluffypets.mvc.model.Category;
import com.fluffypets.dao.*;

import java.util.List;

public interface CategoryDAO extends GenericDAO<Category> {

    List<Category> getAll();

}
