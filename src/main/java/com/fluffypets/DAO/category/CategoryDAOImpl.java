package com.fluffypets.DAO.category;

import com.fluffypets.DAO.AbstractDAO;
import exeptions.DAOException;
import com.fluffypets.MVC.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl extends AbstractDAO<Category> implements CategoryDAO,AutoCloseable {

    public CategoryDAOImpl(Connection connection) {
        super(connection);
        createTableIfNotExists();
    }

    @Override
    protected void createTableIfNotExists() throws DAOException {
        String initialQuery = "CREATE TABLE IF NOT EXISTS `Pets`.`categories` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`categoryName` VARCHAR(256) NOT NULL," +
                "`categoryDescription` VARCHAR(256) NOT NULL," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC))";
        try {
            Statement statement = connection.createStatement();
            statement.execute(initialQuery);
        } catch (SQLException e) {
            throw new DAOException("Table categories creation error");
        }
    }

    @Override
    public Category create(Category category) {
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "INSERT INTO Pets.categories (categoryName,categoryDescription) VALUES(?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getCategoryDescription());
            preparedStatement.execute();

            return get(category);
        } catch (SQLException e) {
            throw new DAOException("There are problems with new category insertion to DB" + e);
        }
    }

    @Override
    public Category delete(Category category) {
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "DELETE FROM Pets.categories  WHERE categoryName = ? AND categoryDescription =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getCategoryDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("There are problems with category deleting from DB" + e);
        }
        return category;
    }

    @Override
    public Category update(Category category) {
        PreparedStatement preparedStatement;
        try {
            String preparedQuery = "UPDATE Pets.categories SET categoryName = ?,categoryDescription=? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getCategoryDescription());
            preparedStatement.setLong(3, category.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("There are problems with new category update in DB" + e);
        }
        return category;
    }

    @Override
    public Category get(Category category) {
        PreparedStatement preparedStatement;
        try {
            String preparedQuery = "SELECT * FROM Pets.categories WHERE categoryName=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, category.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String categoryName = resultSet.getString("categoryName");
                String categoryDescription = resultSet.getString("categoryDescription");
                int id = resultSet.getInt("id");
                category = new Category(id, categoryName,categoryDescription);
            } else category = null;
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting category from DB" + e);
        }
        return category;
    }

    @Override
    public Category findById(Integer id) {
        PreparedStatement preparedStatement;
        Category category;
        try {
            String preparedQuery = "SELECT * FROM Pets.categories WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String categoryName = resultSet.getString("categoryName");
                String categoryDescription = resultSet.getString("categoryDescription");
                category = new Category(id, categoryName,categoryDescription);
                return category;
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems searching category by id " + e);
        }
        return null;
    }

    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        Category category;
        Integer id;
        String name;
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "SELECT * FROM Pets.categories";
            preparedStatement = connection.prepareStatement(preparedQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("categoryName");
                String categoryDescription = rs.getString("categoryDescription");
                category = new Category(id, name,categoryDescription);
                list.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
