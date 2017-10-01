package com.fluffypets.dao.category;

import com.fluffypets.dao.AbstractDAO;
import com.fluffypets.dao.product.ProductDAO;
import com.fluffypets.dao.product.ProductDAOImpl;
import com.fluffypets.mvc.model.Category;
import com.fluffypets.mvc.model.Product;
import exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl extends AbstractDAO<Category> implements CategoryDAO, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(CategoryDAOImpl.class.getName());

    public CategoryDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Category create(Category category) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "INSERT INTO Pets.categories (categoryName,categoryDescription) VALUES(?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getCategoryDescription());
            preparedStatement.execute();
            logger.info("create category query");
            return get(category);
        } catch (SQLException e) {
            throw new DAOException("There are problems with new category insertion to DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public Category delete(Category category) {
        PreparedStatement preparedStatement = null;
        try {
            ProductDAO productDAO = new ProductDAOImpl(this.connection);
            List<Product> products = productDAO.selectByCategoryAndPrice(category.getId().toString(), 0, Integer.MAX_VALUE);
            for (Product product : products) productDAO.delete(product);

            String preparedQuery = "DELETE FROM Pets.categories  WHERE categoryName = ? AND categoryDescription =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getCategoryDescription());
            preparedStatement.execute();
            logger.info("delete category query");
        } catch (SQLException e) {
            throw new DAOException("There are problems with category deleting from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return category;
    }

    @Override
    public Category update(Category category) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "UPDATE Pets.categories SET categoryName = ?,categoryDescription=? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getCategoryDescription());
            preparedStatement.setLong(3, category.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("There are problems with new category update in DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return category;
    }

    @Override
    public Category get(Category category) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "SELECT * FROM Pets.categories WHERE categoryName=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, category.getName());
            ResultSet rs = preparedStatement.executeQuery();
            List<Category> resSetCont = parseResultSet(rs);
            if (resSetCont.size() == 0) {
                category = null;
            } else {
                category = resSetCont.get(0);
            }
            logger.info("get category query");
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting category from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return category;
    }

    @Override
    public Category findById(Integer id) {
        PreparedStatement preparedStatement = null;
        Category category;
        try {
            String preparedQuery = "SELECT * FROM Pets.categories WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            List<Category> resSetCont = parseResultSet(rs);
            if (resSetCont.size() == 0) {
                category = null;
            } else {
                category = resSetCont.get(0);
            }
            logger.info("findById category query");
        } catch (SQLException e) {
            throw new DAOException("There are problems searching category by id " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return category;
    }

    public List<Category> getAll() {
        List<Category> list;
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "SELECT * FROM Pets.categories";
            preparedStatement = connection.prepareStatement(preparedQuery);
            ResultSet rs = preparedStatement.executeQuery();
            list = parseResultSet(rs);
            logger.info("find category By Id query");
        } catch (SQLException e) {
            throw new DAOException("getAll list query error" + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return list;
    }

    @Override
    public List<Category> parseResultSet(ResultSet rs) {
        List<Category> list = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("categoryName");
                String categoryDescription = rs.getString("categoryDescription");
                Category category = new Category(id, name, categoryDescription);
                list.add(category);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public void close() {
        logger.info("Close connection from category dao");
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
    }
}
