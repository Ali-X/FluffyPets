package com.fluffypets.services.impl;

import com.fluffypets.dao.CategoryDAO;
import com.fluffypets.dao.impl.CategoryDAOImpl;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.entities.Category;
import com.fluffypets.services.CategoryService;
import com.fluffypets.exeptions.DAOException;

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
                if (existing == null) existing= categoryDAO.create(category);
                connection.commit();
                return existing;
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
