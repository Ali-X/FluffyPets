package com.fluffypets.servicies.product;

import com.fluffypets.dao.category.CategoryDAO;
import com.fluffypets.dao.category.CategoryDAOImpl;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.mvc.model.Category;
import exeptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> getAll() {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                CategoryDAO categoryDAO = new CategoryDAOImpl(connection);
                List<Category> categories = categoryDAO.getAll();
                connection.commit();
                return categories;
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Transaction error " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Category create(Category category) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                CategoryDAO categoryDAO = new CategoryDAOImpl(connection);
                Category existing = categoryDAO.get(category);
                connection.commit();
                if (existing == null) return categoryDAO.create(category);
                else {
                    return existing;
                }
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Transaction error " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Category get(Integer id) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                CategoryDAO categoryDAO = new CategoryDAOImpl(connection);
                Category category = categoryDAO.findById(id);
                connection.commit();
                return category;
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Transaction error " + e.getLocalizedMessage());
        }
        return null;
    }
}
