package com.fluffypets.DAO.orders;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.DAO.category.CategoryDAOImpl;
import com.fluffypets.MVC.model.Order;
import com.fluffypets.MVC.model.OrderItem;
import exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(OrderDAOImpl.class.getName());

    private OrderItemDAO itemDAO;

    public OrderDAOImpl(Connection connection) {
        super(connection);
        itemDAO = new OrderItemDAOImpl(connection);
    }

    @Override
    protected void createTableIfNotExists() throws DAOException {
        Statement statement = null;
        String initialQuery = "CREATE TABLE IF NOT EXISTS `Pets`.`orders` (  `id` INT NOT NULL AUTO_INCREMENT, " +
                "`userId` INT NOT NULL,  `dateOfOrder` DATE NOT NULL,  `orderStatus` VARCHAR(16) NOT NULL," +
                "`dateOfDelivery` DATE,`comment` VARCHAR(128),PRIMARY KEY (`id`), " +
                "UNIQUE INDEX `id_UNIQUE` (`id` ASC),CONSTRAINT FOREIGN KEY (userId) REFERENCES users(id))";
        try {
            statement = connection.createStatement();
            statement.execute(initialQuery);
            logger.info("createTableIfNotExists query from order DAO");
        } catch (SQLException e) {
            logger.error("Table order creation error\n" + e);
            throw new DAOException("Table order creation error");
        } finally {
            closeStatement(statement, logger);
        }
    }

    @Override
    public List<Order> getMyOrders(Integer userId) {
        List<Order> list = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        try {
            String preparedQuery = "SELECT * FROM Pets.orders WHERE userId=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                LocalDate dateOfOrder = resultSet.getDate("dateOfOrder").toLocalDate();
                String orderStatus = resultSet.getString("orderStatus");
                LocalDate dateOfDelivery = resultSet.getDate("dateOfDelivery").toLocalDate();
                String comment = resultSet.getString("comment");
                List<OrderItem> items = itemDAO.getAllItems(id);
                list.add(new Order(id, userId, dateOfOrder, dateOfDelivery, orderStatus, items, comment));
                logger.info("getMyOrders query");
            }
        } catch (SQLException e) {
            logger.error("There are problems with getting all orders from DB\n" + e);
            throw new DAOException("There are problems with getting all orders from DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return list;
    }

    @Override
    public Order create(Order order) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "INSERT INTO Pets.orders (userId, dateOfOrder, orderStatus, dateOfDelivery, comment) VALUES(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setDate(2, Date.valueOf(order.getOrderDate()));
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setDate(4, Date.valueOf(order.getDeliveryDate()));
            preparedStatement.setString(5, order.getComment());
            preparedStatement.execute();
            Order theOrder = get(order);
            for (OrderItem item : order.getItems()) {
                item.setOrderId(theOrder.getOrderId());
                itemDAO.create(item);
            }
            theOrder = get(order);
            logger.info("Order create query");
            return theOrder;
        } catch (SQLException e) {
            logger.error("There are problems with new order insertion to DB\n" + e);
            throw new DAOException("There are problems with new order insertion to DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public Order delete(Order order) {
        PreparedStatement preparedStatement = null;
        try {
            for (OrderItem item : order.getItems()) {
                itemDAO.delete(item);
            }
            String preparedQuery = "DELETE FROM Pets.orders  WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.execute();
            logger.info("Order delete query");
        } catch (SQLException e) {
            logger.error("There are problems with order deleting from DB\n" + e);
            throw new DAOException("There are problems with order deleting from DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return order;
    }

    @Override
    public Order update(Order order) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "UPDATE Pets.orders SET  userId = ?,dateOfOrder = ?, orderStatus = ?, dateOfDelivery = ?, comment=? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setDate(2, Date.valueOf(order.getOrderDate()));
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setDate(4, Date.valueOf(order.getDeliveryDate()));
            preparedStatement.setString(5, order.getComment());
            preparedStatement.execute();
            for (OrderItem item : order.getItems()) {
                if (itemDAO.get(item) != null) {
                    itemDAO.update(item);
                } else {
                    itemDAO.create(item);
                }
            }
            logger.info("Order update query");
        } catch (SQLException e) {
            logger.error("There are problems with order item update in DB \n" + e);
            throw new DAOException("There are problems with order item update in DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return order;
    }

    @Override
    public Order get(Order order) {
        PreparedStatement preparedStatement = null;
        Order theOrder = null;
        try {
            String preparedQuery = "SELECT * FROM Pets.orders WHERE userId = ? AND dateOfOrder = ? AND orderStatus = ?" +
                    "AND dateOfDelivery = ? AND comment = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setDate(2, Date.valueOf(order.getOrderDate()));
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setDate(4, Date.valueOf(order.getDeliveryDate()));
            preparedStatement.setString(5, order.getComment());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                List<OrderItem> items = itemDAO.getAllItems(id);
                theOrder = new Order(id, order.getUserId(), order.getOrderDate(),
                        order.getDeliveryDate(), order.getStatus(), items, order.getComment());
                logger.info("Order get query");
                return theOrder;
            }
        } catch (SQLException e) {
            logger.error("There are problems with getting orders from DB \n" + e);
            throw new DAOException("There are problems with getting orders from DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return null;
    }

    @Override
    public Order findById(Integer id) {
        PreparedStatement preparedStatement = null;
        Order theOrder = null;
        try {
            String preparedQuery = "SELECT * FROM Pets.orders WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer userId = resultSet.getInt("userId");
                LocalDate dateOfOrder = resultSet.getDate("dateOfOrder").toLocalDate();
                String orderStatus = resultSet.getString("orderStatus");
                LocalDate dateOfDelivery = resultSet.getDate("dateOfDelivery").toLocalDate();
                String comment = resultSet.getString("comment");
                List<OrderItem> items = itemDAO.getAllItems(id);
                theOrder = new Order(id, userId, dateOfOrder, dateOfDelivery, orderStatus, items, comment);
                logger.info("Order findById query");
                return theOrder;
            }
        } catch (SQLException e) {
            logger.error("There are problems with getting orders by id from DB \n" + e);
            throw new DAOException("There are problems with getting orders by id from DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return null;
    }


    @Override
    public void close() throws Exception {
        logger.info("Connection close from product DAO");
        connection.close();
    }
}
