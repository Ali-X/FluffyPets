package com.fluffypets.services.impl;

import com.fluffypets.dao.ProductDAO;
import com.fluffypets.entities.Product;
import com.fluffypets.exeptions.DAOException;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.services.ProductService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.fluffypets.dao.impl.DaoFactory.getProductDAO;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getAllSelected(String categIds, int min, int max) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                ProductDAO productDAO = getProductDAO(connection);
                List<Product> products = productDAO.selectByCategoryAndPrice(categIds, min, max);
                connection.commit();
                return products;
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
    public Integer countSelected(String categoryIds, int min, int max, int paginationStep) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                ProductDAO productDAO = getProductDAO(connection);
                Integer productsNum = productDAO.countSelected(categoryIds, min, max, paginationStep);
                connection.commit();
                return productsNum;
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
    public List<Product> selectAndPagination(String categoryIds, int min, int max, String order, Integer paginationStep, Integer pagination) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                ProductDAO productDAO = getProductDAO(connection);
                List<Product> products = productDAO.selectAndPagination(categoryIds, min, max, order, paginationStep, pagination);
                connection.commit();
                return products;
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
    public Product getProduct(Product product) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                ProductDAO productDAO = getProductDAO(connection);
                Product theProduct = productDAO.get(product);
                connection.commit();
                return theProduct;
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
    public Product getProductById(Integer id) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                ProductDAO productDAO = getProductDAO(connection);
                Product product = productDAO.findById(id);
                connection.commit();
                return product;
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
    public Product create(Product product) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                ProductDAO productDAO = getProductDAO(connection);
                Product existing = productDAO.get(product);
                if (existing == null) existing = productDAO.create(product);
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
    public Product delete(Product product) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                ProductDAO productDAO = getProductDAO(connection);
                Product product1 = productDAO.delete(product);
                connection.commit();
                return product1;
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
    public Product update(Product product) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                ProductDAO productDAO = getProductDAO(connection);
                Product product1 = productDAO.update(product);
                connection.commit();
                return product1;
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
