package com.fluffypets.DAO.product;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.DAO.DAOException;
import com.fluffypets.MVC.model.Product;
import com.fluffypets.MVC.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductDAOImpl extends AbstractDAO<Product> implements ProductDAO {
    protected ProductDAOImpl(Connection connection) {
        super(connection);
    }
/*
    private Integer id;
    private String name;
    private String producer;
    private BigDecimal price;
    private String description;
    private String pictureURL;
    private List<Category> category;
*/
    @Override
    protected void createTableIfNotExists() throws DAOException {
        String initialQuery = "CREATE TABLE IF NOT EXISTS `Pets`.`users` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`productName` VARCHAR(256) NOT NULL," +
                "`producer` VARCHAR(256) NOT NULL," +
                "`price` VARCHAR(256) NOT NULL," +
                "`description` VARCHAR(256) NOT NULL," +
                "`pictureURL` VARCHAR(128) NOT NULL," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC))";
        try {
            Statement statement = connection.createStatement();
            statement.execute(initialQuery);
        } catch (SQLException e) {
            throw new DAOException("Table products creation error");
        }
    }

    @Override
    public List<Product> getAll(Integer c_id) {
        return null;
    }

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Product delete(Product item) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public Product get(Product product) {
        return null;
    }

    @Override
    public Product findById(Integer id) {
        return null;
    }

}
