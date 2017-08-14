package com.fluffypets.DAO.product;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.DAO.DAOException;
import com.fluffypets.MVC.model.Product;
import com.fluffypets.MVC.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductDAOImpl extends AbstractDAO<Product> implements ProductDAO {
    protected ProductDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected void createTableIfNotExists() throws DAOException {
        String initialQuery = "CREATE TABLE IF NOT EXISTS `Pets`.`products` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`productName` VARCHAR(256) NOT NULL," +
                "`producer` VARCHAR(256) NOT NULL," +
                "`price` VARCHAR(256) NOT NULL," +
                "`description` VARCHAR(256) NOT NULL," +
                "`pictureURL` VARCHAR(128) NOT NULL," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC))";

        String productCategoryTable = "CREATE TABLE IF NOT EXISTS `Pets`.`productCategory` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "`userId` INT NOT NULL,\n" +
                "  `categoryId` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC),\n" +
                "  CONSTRAINT FOREIGN KEY (userId) REFERENCES users(id),\n" +
                "  CONSTRAINT FOREIGN KEY (categoryId) REFERENCES categories(id))";
        try {
            Statement statement = connection.createStatement();
            statement.execute(initialQuery);
            Statement statement2 = connection.createStatement();
            statement2.execute(productCategoryTable);
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
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "INSERT INTO Pets.users (productName, producer, price, description,pictureURL) VALUES(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getProducer());
            preparedStatement.setString(3, product.getPrice().toString());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setString(5, product.getPictureURL());
            preparedStatement.execute();
            // TODO: 8/14/17 add categories insertion to DB
            return get(product);
        } catch (SQLException e) {
            throw new DAOException("There are problems with new product insertion to DB" + e);
        }
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
