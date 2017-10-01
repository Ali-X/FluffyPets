package com.fluffypets.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoFactory {
    private static final Logger logger = LogManager.getLogger(DaoFactory.class.getName());


    public static void initialQueries() {
        String initialQ1 = "SET NAMES 'utf8'";
        String initialQ2 = "SET CHARACTER SET 'utf8'";
        String initialQ3 = "SET SESSION collation_connection = 'utf8_general_ci'";

        String initialCategories = "CREATE TABLE IF NOT EXISTS `Pets`.`categories` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`categoryName` VARCHAR(32) NOT NULL UNIQUE ," +
                "`categoryNameUa` VARCHAR(32)," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC))";

        String initialUsers = "CREATE TABLE IF NOT EXISTS `Pets`.`users` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`userName` VARCHAR(32) NOT NULL UNIQUE ," +
                "`password` VARCHAR(32) NOT NULL," +
                "`email` VARCHAR(128) NOT NULL UNIQUE," +
                "`roleString` VARCHAR(32) NOT NULL," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC))";

        String initialProducts = "CREATE TABLE IF NOT EXISTS `Pets`.`products` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`productName` VARCHAR(128) NOT NULL," +
                "`producer` VARCHAR(128)," +
                "`price` VARCHAR(12) NOT NULL," +
                "`description` VARCHAR(500)," +
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

        String initialUserData = "CREATE TABLE IF NOT EXISTS `Pets`.`userAdress` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`userId` INT NOT NULL," +
                "`fullName` VARCHAR(100) NOT NULL ," +
                "`district` VARCHAR(30) NOT NULL," +
                "`area` VARCHAR(30) NOT NULL," +
                "`street` VARCHAR(30) NOT NULL," +
                "`app` VARCHAR(30) NOT NULL," +
                "`phone` VARCHAR(20) NOT NULL," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                " CONSTRAINT FOREIGN KEY (userId) REFERENCES users(id))";

        String initialCourier = "CREATE TABLE IF NOT EXISTS `Pets`.`courier`" +
                "(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "    name VARCHAR(45) NOT NULL," +
                "    surname VARCHAR(45) NOT NULL," +
                "    passportNumber VARCHAR(45) NOT NULL);";

        String initialStorage ="CREATE TABLE IF NOT EXISTS `Pets`.`storage`" +
                "(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "productId INT NOT NULL, " +
                "avaliableHere INT NOT NULL," +
                "status VARCHAR(45)," +
                "CONSTRAINT storage_products_id_fk FOREIGN KEY (productId) REFERENCES products (id));";

        String initialDelivery ="CREATE TABLE IF NOT EXISTS `Pets`.`delivery`" +
                "(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "    courierId INT NOT NULL," +
                "    orderId INT NOT NULL," +
                "    status VARCHAR(45) NOT NULL," +
                "    start DATE NOT NULL," +
                "    finish DATE," +
                "    CONSTRAINT delivery_courier_id_fk FOREIGN KEY (courierId) REFERENCES courier (id)," +
                "    CONSTRAINT delivery_orders_id_fk FOREIGN KEY (orderId) REFERENCES orders (id));";

        Statement statement = null;
        try (Connection connection = ContextFactory.getContextConnection()) {
            statement = connection.createStatement();
            statement.execute(initialQ1);
            statement.execute(initialQ2);
            statement.execute(initialQ3);
            statement.execute(initialCategories);
            statement.execute(initialUsers);
            statement.execute(initialProducts);
            statement.execute(initialOrders);
            statement.execute(initialOrdersItems);
            statement.execute(initialUserData);
            statement.execute(initialCourier);
            statement.execute(initialStorage);
            statement.execute(initialDelivery);
        } catch (SQLException e) {
            logger.error("Problems with Database Initialization");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e.getLocalizedMessage());
            }
        }
    }
}