package com.fluffypets.DAO.category;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.DAO.product.ProductDAO;
import com.fluffypets.DAO.product.ProductDAOImpl;
import com.fluffypets.MVC.model.Product;
import com.fluffypets.factory.ContextFactory;
import exeptions.DAOException;
import com.fluffypets.MVC.model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl extends AbstractDAO<Category> implements CategoryDAO, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(CategoryDAOImpl.class.getName());

    private static CategoryDAO instance = new CategoryDAOImpl();
    private static ProductDAO productDAO= ProductDAOImpl.getOrderItemDAOImpl();

    public static CategoryDAO getCategoryDAOImpl() {
        return instance;
    }

    private CategoryDAOImpl() {
        super(ContextFactory.getContextConnection());
    }

    @Override
    public Category create(Category category) {
            PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "INSERT INTO Pets.categories (categoryName,categoryDescription) VALUES(?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getCategoryDescription());
            preparedStatement.execute();
            logger.info("create category query");

            return get(category);
        } catch (SQLException e) {
            logger.error("There are problems with new category insertion to DB\n" + e);
            throw new DAOException("There are problems with new category insertion to DB" + e);
        } finally {
            closeStatement(preparedStatement,logger);
        }
    }

    @Override
    public Category delete(Category category) {
            PreparedStatement preparedStatement=null;
        try {
            List<Product> products=productDAO.selectByCategoryAndPrice(category.getId().toString(),0,Integer.MAX_VALUE);
            for (Product product:products)productDAO.delete(product);

            String preparedQuery = "DELETE FROM Pets.categories  WHERE categoryName = ? AND categoryDescription =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getCategoryDescription());
            preparedStatement.execute();
            logger.info("delete category query");
        } catch (SQLException e) {
            logger.error("There are problems with category deleting from DB\n" + e);
            throw new DAOException("There are problems with category deleting from DB" + e);
        } finally {
            closeStatement(preparedStatement,logger);
        }
        return category;
    }

    @Override
    public Category update(Category category) {
        PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "UPDATE Pets.categories SET categoryName = ?,categoryDescription=? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getCategoryDescription());
            preparedStatement.setLong(3, category.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("There are problems with new category update in DB\n" + e);
            throw new DAOException("There are problems with new category update in DB" + e);
        } finally {
            closeStatement(preparedStatement,logger);
        }
        return category;
    }

    @Override
    public Category get(Category category) {
        PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "SELECT * FROM Pets.categories WHERE categoryName=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, category.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String categoryName = resultSet.getString("categoryName");
                String categoryDescription = resultSet.getString("categoryDescription");
                int id = resultSet.getInt("id");
                category = new Category(id, categoryName, categoryDescription);
            } else category = null;
            logger.info("get category query");
        } catch (SQLException e) {
            logger.error("There are problems with getting category from DB\n" + e);
            throw new DAOException("There are problems with getting category from DB" + e);
        } finally {
            closeStatement(preparedStatement,logger);
        }
        return category;
    }

    @Override
    public Category findById(Integer id) {
        PreparedStatement preparedStatement=null;
        Category category;
        try {
            String preparedQuery = "SELECT * FROM Pets.categories WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String categoryName = resultSet.getString("categoryName");
                String categoryDescription = resultSet.getString("categoryDescription");
                category = new Category(id, categoryName, categoryDescription);
                return category;
            }
            logger.info("findById category query");
        } catch (SQLException e) {
            logger.error("There are problems searching category by id\n" + e);
            throw new DAOException("There are problems searching category by id" + e);
        } finally {
            closeStatement(preparedStatement,logger);
        }
        return null;
    }

    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        Category category;
        Integer id;
        String name;
            PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "SELECT * FROM Pets.categories";
            preparedStatement = connection.prepareStatement(preparedQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("categoryName");
                String categoryDescription = rs.getString("categoryDescription");
                category = new Category(id, name, categoryDescription);
                list.add(category);
            }
            logger.info("find category By Id query");

        } catch (SQLException e) {
            logger.error("category list query error\n" + e);
            throw new DAOException("getAll list query error" + e);
        } finally {
            closeStatement(preparedStatement,logger);
        }
        return list;
    }


    @Override
    public void close() throws Exception {
        logger.info("Close connection from category DAO");
        this.connection.close();
    }
}
