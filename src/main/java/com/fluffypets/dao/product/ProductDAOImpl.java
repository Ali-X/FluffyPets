package com.fluffypets.dao.product;

import com.fluffypets.dao.AbstractDAO;
import com.fluffypets.dao.category.CategoryDAO;
import com.fluffypets.mvc.model.Category;
import com.fluffypets.mvc.model.Product;
import com.fluffypets.factory.DaoFactory;
import com.fluffypets.factory.ContextFactory;
import exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
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

    public ProductDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Product create(Product product) {
        PreparedStatement preparedStatement = null;
        try {
            this.connection.setAutoCommit(false);
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
            this.connection.commit();
            logger.info("create product query");
            return get(product);
        } catch (SQLException e) {
            logger.error("There are problems with new product insertion to DB\n" + e);
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("rollback " + e1.getLocalizedMessage());
            }
            throw new DAOException("There are problems with new product insertion to DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public Product delete(Product item) {
        PreparedStatement preparedStatement = null;
        try {
            this.connection.setAutoCommit(false);
            String preparedQuery = "DELETE FROM Pets.products  WHERE productName = ? AND producer=? " +
                    "AND price=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getProducer());
            preparedStatement.setString(3, item.getPrice().toString());
            preparedStatement.execute();
            this.connection.commit();
            logger.info("delete product query");
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("rollback " + e1.getLocalizedMessage());
            }
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
            this.connection.setAutoCommit(false);
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
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("rollback " + e1.getLocalizedMessage());
            }
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
            this.connection.setAutoCommit(false);
            String preparedQuery = "SELECT * FROM Pets.products WHERE productName = ? AND producer = ? AND price = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getProducer());
            preparedStatement.setString(3, product.getPrice().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            product = parseResultSet(resultSet).get(0);
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("rollback " + e1.getLocalizedMessage());
            }
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
            this.connection.setAutoCommit(false);
            String preparedQuery = "SELECT * FROM Pets.products WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            product = parseResultSet(resultSet).get(0);
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("rollback " + e1.getLocalizedMessage());
            }
            throw new DAOException("There are problems searching product by id " + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        PreparedStatement preparedStatement = null;
        List<Product> products;
        try {
            this.connection.setAutoCommit(false);
            String preparedQuery = "SELECT * FROM Pets.products ORDER BY CAST(price AS DECIMAL(9,2))";
            preparedStatement = connection.prepareStatement(preparedQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            products = parseResultSet(resultSet);
            this.connection.commit();
            return products;
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("rollback " + e1.getLocalizedMessage());
            }
            throw new DAOException("There are problems searching product by category id " + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public List<Product> selectByCategoryAndPrice(String categoryIds, int min, int max) {
        PreparedStatement preparedStatement = null;
        List<Product> products;
        try {
            this.connection.setAutoCommit(false);
            String preparedQuery = "SELECT * FROM Pets.products WHERE price>=? AND price<=? AND categoryId IN(" + categoryIds + ")";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            products = parseResultSet(resultSet);
            this.connection.commit();
            return products;
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("rollback " + e1.getLocalizedMessage());
            }
            throw new DAOException("There are problems searching product by category id " + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public Integer countSelected(String categoryIds, int min, int max, int paginationStep) {
        PreparedStatement preparedStatement = null;
        try {
            this.connection.setAutoCommit(false);
            String preparedQuery = "SELECT COUNT(*) FROM Pets.products WHERE price>=? AND price<=? AND categoryId IN(" + categoryIds + ")";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            Integer result = 0;
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            result = (int) Math.ceil(result / ((double) paginationStep));
            this.connection.commit();
            return result;
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("rollback " + e1.getLocalizedMessage());
            }
            throw new DAOException("There are problems with counting products " + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public List<Product> selectAndPagination(String categoryIds, int min, int max, String order, Integer paginationStep, Integer pagination) {
        PreparedStatement preparedStatement = null;
        List<Product> products;
        try {
            this.connection.setAutoCommit(false);
            String preparedQuery = "SELECT * FROM Pets.products WHERE price>=? AND price<=? AND categoryId IN(" + categoryIds + ") ORDER BY CAST(price as DECIMAL(9,2)) " + order + " LIMIT ?,?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            preparedStatement.setString(3, order);
            preparedStatement.setInt(3, (pagination - 1) * paginationStep);
            preparedStatement.setInt(4, paginationStep);
            ResultSet resultSet = preparedStatement.executeQuery();
            products = parseResultSet(resultSet);
            this.connection.commit();
            return products;
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("rollback " + e1.getLocalizedMessage());
            }
            throw new DAOException("There are problems with pagination search " + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public List<Product> parseResultSet(ResultSet resultSet) {
        List<Product> products = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String productName = resultSet.getString("productName");
                String producer = resultSet.getString("producer");
                String price = resultSet.getString("price");
                String description = resultSet.getString("description");
                String pictureURL = resultSet.getString("pictureURL");
                int id = resultSet.getInt("id");
                int categoryId = resultSet.getInt("categoryId");

                CategoryDAO supportReq = DaoFactory.getCategoryDao();
                Category thisCategory = supportReq.findById(categoryId);

                products.add(new Product(id, productName, producer, new BigDecimal(price),
                        description, pictureURL, thisCategory));
            }
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
        return products;
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
    }
}
