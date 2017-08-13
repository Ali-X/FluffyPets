package com.fluffypets.DAO.category;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.DAO.DAOException;
import com.fluffypets.MVC.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl extends AbstractDAO<Category> implements CategoryDAO {

    public CategoryDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected void createTableIfNotExists() throws DAOException {
    }

    @Override
    public Category create(Category category) {
        return null;
    }

    @Override
    public Category delete(Category category) {
        return null;
    }

    @Override
    public Category update(Category category) {
        return null;
    }

    @Override
    public Category get(Category category) {
        return null;
    }

    @Override
    public Category findById(Integer id) {
        return null;
    }

    public List<Category> getAll() {
        List<Category> list = new ArrayList<Category>();
        Category category = null;
        Integer id = null;
        String name = null;
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "SELECT * FROM CATEGORY";
            preparedStatement = connection.prepareStatement(preparedQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("ID");
                name = rs.getString("NAME");
                category = new Category(id, name);
                list.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
