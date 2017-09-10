package com.fluffypets.servicies;

import com.fluffypets.DAO.category.CategoryDAO;
import com.fluffypets.MVC.model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class.getName());

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
