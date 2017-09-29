package com.fluffypets.servicies.product;

import com.fluffypets.dao.category.CategoryDAO;
import com.fluffypets.mvc.model.Category;
import exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategoryServiceImpl implements CategoryService,AutoCloseable {
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

    @Override
    public void close() throws DAOException {
        categoryDAO.close();
    }
}
