package com.fluffypets.DAO.product;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.DAO.category.CategoryDAO;
import com.fluffypets.MVC.model.Category;
import com.fluffypets.MVC.model.Product;
import com.fluffypets.factory.DaoFactory;
import com.fluffypets.factory.ContextFactory;
import exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl extends AbstractDAO<Product> implements ProductDAO, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(ProductDAOImpl.class.getName());

    private static ProductDAO instance = new ProductDAOImpl();

    public static ProductDAO getOrderItemDAOImpl() {
        return instance;
    }

    private ProductDAOImpl() {
        super(ContextFactory.getContextConnection());
    }

    @Override
    public Product create(Product product) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "INSERT INTO Pets.products (productName, producer, price, " +
                    "description,pictureURL,categoryId) VALUES(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getProducer());
            preparedStatement.setString(3, product.getPrice().setScale(2, BigDecimal.ROUND_CEILING).toString());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setString(5, product.getPictureURL());
            preparedStatement.setInt(6, product.getCategory().getId());
            preparedStatement.execute();
            logger.info("create product query");
            return get(product);
        } catch (SQLException e) {
            logger.error("There are problems with new product insertion to DB\n" + e);
            throw new DAOException("There are problems with new product insertion to DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public Product delete(Product item) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "DELETE FROM Pets.products  WHERE productName = ? AND producer=? " +
                    "AND price=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getProducer());
            preparedStatement.setString(3, item.getPrice().toString());
            preparedStatement.execute();
            logger.info("delete product query");
        } catch (SQLException e) {
            throw new DAOException("There are problems with product deleting from DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return item;
    }

    @Override
    public Product update(Product product) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "UPDATE Pets.products SET productName = ?," +
                    "producer = ?, price = ?, description = ?, pictureURL = ?, categoryId= ? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getProducer());
            preparedStatement.setString(3, product.getPrice().setScale(2, BigDecimal.ROUND_CEILING).toString());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setString(5, product.getPictureURL());
            preparedStatement.setLong(6, product.getCategory().getId());
            preparedStatement.setLong(7, product.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("There are problems with new user update in DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return product;
    }

    @Override
    public Product get(Product product) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "SELECT * FROM Pets.products WHERE productName = ? AND producer = ? AND price = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getProducer());
            preparedStatement.setString(3, product.getPrice().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String productName = resultSet.getString("productName");
                String producer = resultSet.getString("producer");
                String price = resultSet.getString("price");
                String description = resultSet.getString("description");
                String pictureURL = resultSet.getString("pictureURL");
                int id = resultSet.getInt("id");
                int categoryId = resultSet.getInt("categoryId");

                CategoryDAO supportReq = DaoFactory.getCategoryDao();

                product = new Product(id, productName, producer, new BigDecimal(price),
                        description, pictureURL, supportReq.findById(categoryId));
            } else product = null;
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting product from DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return product;
    }

    @Override
    public Product findById(Integer id) {
        PreparedStatement preparedStatement = null;
        Product product;
        try {
            String preparedQuery = "SELECT * FROM Pets.products WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String productName = resultSet.getString("productName");
                String producer = resultSet.getString("producer");
                String price = resultSet.getString("price");
                String description = resultSet.getString("description");
                String pictureURL = resultSet.getString("pictureURL");
                int categoryId = resultSet.getInt("categoryId");

                CategoryDAO supportReq = DaoFactory.getCategoryDao();

                product = new Product(id, productName, producer, new BigDecimal(price),
                        description, pictureURL, supportReq.findById(categoryId));
                return product;
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems searching product by id " + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        PreparedStatement preparedStatement = null;
        List<Product> product = new ArrayList<>();
        CategoryDAO supportReq = DaoFactory.getCategoryDao();
        try {
            String preparedQuery = "SELECT * FROM Pets.products ORDER BY LENGTH(price),price";
            preparedStatement = connection.prepareStatement(preparedQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String productName = resultSet.getString("productName");
                String producer = resultSet.getString("producer");
                String price = resultSet.getString("price");
                String description = resultSet.getString("description");
                String pictureURL = resultSet.getString("pictureURL");
                int id = resultSet.getInt("id");

                Category thisCategory = supportReq.findById(id);

                product.add(new Product(id, productName, producer, new BigDecimal(price),
                        description, pictureURL, thisCategory));
            }
            return product;
        } catch (SQLException e) {
            throw new DAOException("There are problems searching product by category id " + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public List<Product> selectByCategoryAndPrice(String categoryIds, int min, int max) {
        PreparedStatement preparedStatement = null;
        List<Product> product = new ArrayList<>();
        CategoryDAO supportReq = DaoFactory.getCategoryDao();
        try {
            String preparedQuery = "SELECT * FROM Pets.products WHERE price>=? AND price<=? " +
                    "AND categoryId IN(" + categoryIds + ")";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String productName = resultSet.getString("productName");
                String producer = resultSet.getString("producer");
                String price = resultSet.getString("price");
                String description = resultSet.getString("description");
                String pictureURL = resultSet.getString("pictureURL");
                int id = resultSet.getInt("id");

                Category thisCategory = supportReq.findById(id);

                product.add(new Product(id, productName, producer, new BigDecimal(price),
                        description, pictureURL, thisCategory));
            }
            return product;
        } catch (SQLException e) {
            throw new DAOException("There are problems searching product by category id " + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
