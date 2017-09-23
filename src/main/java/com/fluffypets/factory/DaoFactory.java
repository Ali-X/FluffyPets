package com.fluffypets.factory;

import com.fluffypets.DAO.category.CategoryDAO;
import com.fluffypets.DAO.category.CategoryDAOImpl;
import com.fluffypets.DAO.orders.OrderDAO;
import com.fluffypets.DAO.orders.OrderDAOImpl;
import com.fluffypets.DAO.orders.OrderItemDAO;
import com.fluffypets.DAO.orders.OrderItemDAOImpl;
import com.fluffypets.DAO.product.ProductDAO;
import com.fluffypets.DAO.product.ProductDAOImpl;
import com.fluffypets.DAO.user.UserDAO;
import com.fluffypets.DAO.user.UserDAOImpl;
import com.fluffypets.DAO.user.UserDataDAO;
import com.fluffypets.DAO.user.UserDataDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoFactory {
    private static DaoFactory instance = new DaoFactory();
    private static final Logger logger = LogManager.getLogger(DaoFactory.class.getName());

    public static DaoFactory getInstance() {
        return instance;
    }

    private DaoFactory() {
        String initialQ = "CREATE SCHEMA IF NOT EXISTS `Pets` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;";

        String initialCategories = "CREATE TABLE IF NOT EXISTS `Pets`.`categories` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`categoryName` VARCHAR(32) NOT NULL UNIQUE ," +
                "`categoryDescription` VARCHAR(256)," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC))";

        String initialUsers = "CREATE TABLE IF NOT EXISTS `Pets`.`users` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`userName` VARCHAR(32) NOT NULL UNIQUE ," +
                "`password` VARCHAR(32) NOT NULL," +
                "`email` VARCHAR(128) NOT NULL," +
                "`roleString` VARCHAR(32) NOT NULL," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC))";

        String initialProducts = "CREATE TABLE IF NOT EXISTS `Pets`.`products` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`productName` VARCHAR(128) NOT NULL," +
                "`producer` VARCHAR(128)," +
                "`price` VARCHAR(12) NOT NULL," +
                "`description` VARCHAR(128)," +
                "`pictureURL` VARCHAR(128)," +
                "  `categoryId` INT NOT NULL," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                " CONSTRAINT FOREIGN KEY (categoryId) REFERENCES categories(id))";

        String initialOrders = "CREATE TABLE IF NOT EXISTS `Pets`.`orders` (  " +
                "`id` INT NOT NULL AUTO_INCREMENT, " +
                "`userId` INT NOT NULL,  " +
                "`dateOfOrder` DATE NOT NULL,  " +
                "`orderStatus` VARCHAR(16) NOT NULL," +
                "`dateOfDelivery` DATE," +
                "`comment` VARCHAR(128)," +
                "PRIMARY KEY (`id`), " +
                "UNIQUE INDEX `id_UNIQUE` (`id` ASC),CONSTRAINT FOREIGN KEY (userId) REFERENCES users(id))";

        String initialOrdersItems = "CREATE TABLE IF NOT EXISTS `Pets`.`ordersItems` (`" +
                "id` INT NOT NULL AUTO_INCREMENT," +
                "`orderId` INT NOT NULL," +
                "`productId` INT,`quantity` INT NOT NULL," +
                "`price` VARCHAR(12) NOT NULL," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                "CONSTRAINT FOREIGN KEY (orderId) REFERENCES orders(id)," +
                "CONSTRAINT FOREIGN KEY (productId) REFERENCES products(id))";

        String initialUserData = "CREATE TABLE IF NOT EXISTS `Pets`.`userData` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`userId` INT NOT NULL," +
                "`fullName` VARCHAR(100) NOT NULL ," +
                "`dateOfBirth` DATE," +
                "`gender` VARCHAR(7) NOT NULL," +
                "`maried` BOOLEAN NOT NULL," +
                "`district` VARCHAR(30) NOT NULL," +
                "`area` VARCHAR(30) NOT NULL," +
                "`street` VARCHAR(30) NOT NULL," +
                "`app` VARCHAR(30) NOT NULL," +
                "`primaryPhone` VARCHAR(20) NOT NULL," +
                "`secondaryPhone` VARCHAR(20)," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                " CONSTRAINT FOREIGN KEY (userId) REFERENCES users(id))";

        Statement statement = null;
        try (Connection connection = Factory.getContextConnection()) {
            statement = connection.createStatement();
            statement.execute(initialQ);
            statement.execute(initialCategories);
            statement.execute(initialUsers);
            statement.execute(initialProducts);
            statement.execute(initialOrders);
            statement.execute(initialOrdersItems);
            statement.execute(initialUserData);
        } catch (SQLException e) {
            logger.error("Problems with Database Initialization");
        }
    }

    public static ProductDAO getProductDao() {
        return ProductDAOImpl.getOrderItemDAOImpl();
    }

    public static UserDAO getUserDao() {
        return UserDAOImpl.getOrderItemDAOImpl();
    }

    public static UserDataDAO getUserDataDao() {
        return UserDataDAOImpl.getOrderItemDAOImpl();
    }

    public static CategoryDAO getCategoryDao() {
        return CategoryDAOImpl.getCategoryDAOImpl();
    }

    public static CategoryDAO getCategoryDAO() {
        return CategoryDAOImpl.getCategoryDAOImpl();
    }

    public static OrderDAO getOrderDAO() {
        return OrderDAOImpl.getOrderDAOImpl();
    }

    public static OrderItemDAO getOrderItemDAO() {
        return OrderItemDAOImpl.getOrderItemDAOImpl();
    }
}