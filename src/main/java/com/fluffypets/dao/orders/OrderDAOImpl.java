package com.fluffypets.dao.orders;

import com.fluffypets.dao.AbstractDAO;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.mvc.model.Order;
import com.fluffypets.mvc.model.OrderItem;
import exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(OrderDAOImpl.class.getName());

    private OrderItemDAO itemDAO;

    private static OrderDAO instance = new OrderDAOImpl();

    public static OrderDAO getOrderDAOImpl() {
        return instance;
    }

    private OrderDAOImpl() {
        super(ContextFactory.getContextConnection());
        itemDAO = OrderItemDAOImpl.getOrderItemDAOImpl();
    }

    @Override
    public List<Order> getMyOrders(Integer userId) {
        List<Order> list;
        PreparedStatement preparedStatement = null;

        try {
            String preparedQuery = "SELECT * FROM Pets.orders WHERE userId=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (SQLException e) {
            logger.error("There are problems with getting all orders from DB\n" + e);
            throw new DAOException("There are problems with getting all orders from DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return list;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> list;
        PreparedStatement preparedStatement = null;

        try {
            String preparedQuery = "SELECT * FROM Pets.orders";
            preparedStatement = connection.prepareStatement(preparedQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
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
        return null;
    }

    @Override
    public Order update(Order order) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "UPDATE Pets.orders SET  userId = ?,dateOfOrder = ?, orderStatus = ?, dateOfDelivery = ? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setDate(2, Date.valueOf(order.getOrderDate()));
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setDate(4, Date.valueOf(order.getDeliveryDate()));
            preparedStatement.setInt(5, order.getOrderId());
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
        Order theOrder;
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
            theOrder = parseResultSet(resultSet).get(0);
        } catch (SQLException e) {
            logger.error("There are problems with getting orders from DB \n" + e);
            throw new DAOException("There are problems with getting orders from DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return theOrder;
    }

    @Override
    public Order findById(Integer id) {
        PreparedStatement preparedStatement = null;
        Order theOrder;
        try {
            String preparedQuery = "SELECT * FROM Pets.orders WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            theOrder = parseResultSet(resultSet).get(0);
        } catch (SQLException e) {
            logger.error("There are problems with getting orders by id from DB \n" + e);
            throw new DAOException("There are problems with getting orders by id from DB" + e);
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return theOrder;
    }

    @Override
    public List<Order> parseResultSet(ResultSet resultSet) {
        List<Order> orders = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer userId = resultSet.getInt("userId");
                LocalDate dateOfOrder = resultSet.getDate("dateOfOrder").toLocalDate();
                String orderStatus = resultSet.getString("orderStatus");
                LocalDate dateOfDelivery = resultSet.getDate("dateOfDelivery").toLocalDate();
                String comment = resultSet.getString("comment");
                List<OrderItem> items = itemDAO.getAllItems(id);
                orders.add(new Order(id, userId, dateOfOrder, dateOfDelivery, orderStatus, items, comment));
                logger.info("getAllOrders query");
            }
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
        return orders;
    }

    @Override
    public void close() {
        logger.info("Connection close from product dao");
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
    }
}
