package com.fluffypets.servicies;

import com.fluffypets.DAO.category.CategoryDAO;
import com.fluffypets.MVC.model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    @Override
    public Category create(Category category) {
        Category existing=categoryDAO.get(category);
        if (existing==null) return categoryDAO.create(category);
        else {return existing;}
    }

    @Override
    public Category get(Integer id) {
        return categoryDAO.findById(id);
    }
}
