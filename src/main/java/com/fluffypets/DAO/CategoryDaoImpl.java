package com.fluffypets.DAO;

import com.fluffypets.MVC.model.Category;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

    private static List<Category> categoryList = new ArrayList<>();

    static {
        categoryList.add(new Category(1, "Shoes"));
        categoryList.add(new Category(2, "Dresses"));
        categoryList.add(new Category(3, "Pants"));
    }


    @Override
    public Category create(Category category) {
        categoryList.add(category);
        return category;
    }

    @Override
    public Category delete(Category category) {
        categoryList.remove(category);
        return category;
    }

    @Override
    public Category update(Category category) {
        for (Category thisCat : categoryList) {
            if (thisCat.getId().equals(category.getId())) {
                thisCat = category;
                break;
            }
        }
        return null;
    }

    @Override
    public Category findById(Integer id) {
        for (Category thisCat : categoryList) {
            if (thisCat.getId().equals(id)) {
                return thisCat;
            }
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        return CategoryDaoImpl.categoryList;
    }

    @Override
    public List<Category> findAll() {
        return categoryList;
    }
}
