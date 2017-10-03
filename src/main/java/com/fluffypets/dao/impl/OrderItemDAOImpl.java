package com.fluffypets.dao.impl;

import com.fluffypets.dao.AbstractDAO;
import com.fluffypets.dao.OrderItemDAO;
import com.fluffypets.entities.OrderItem;
import com.fluffypets.exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl extends AbstractDAO<OrderItem> implements OrderItemDAO, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(OrderItemDAOImpl.class.getName());

    OrderItemDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<OrderItem> getAllItems(Integer orderId) {
        List<OrderItem> list;
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "SELECT * FROM Pets.ordersItems WHERE orderId=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseResultSet(resultSet);
            logger.info("get All order Items query from Order Item Dao");
        } catch (SQLException e) {
            throw new DAOException("get All order Items query error " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return list;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "INSERT INTO Pets.ordersItems (orderId, productId, quantity, price) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, orderItem.getOrderId());
            preparedStatement.setLong(2, orderItem.getProductId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setString(4, orderItem.getCurrentPrice().toString());
            preparedStatement.execute();
            logger.info("create Order Item");
            return get(orderItem);
        } catch (SQLException e) {
            throw new DAOException("There are problems with new order item insertion to DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
    }

    @Override
    public OrderItem delete(OrderItem orderItem) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "DELETE FROM Pets.ordersItems  WHERE orderId = ? AND productId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, orderItem.getOrderId());
            preparedStatement.setLong(2, orderItem.getProductId());
            preparedStatement.execute();
            logger.info("delete Order Item");
        } catch (SQLException e) {
            throw new DAOException("There are problems with order item deleting from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return orderItem;
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "UPDATE Pets.ordersItems SET  orderId= ?," +
                    "productId = ?, quantity = ?, price = ? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, orderItem.getOrderId());
            preparedStatement.setLong(2, orderItem.getProductId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setString(4, orderItem.getCurrentPrice().toString());
            preparedStatement.setLong(5, orderItem.getItemId());
            preparedStatement.execute();
            logger.info("update Order Item");
        } catch (SQLException e) {
            throw new DAOException("There are problems with order item update in DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return orderItem;
    }

    @Override
    public OrderItem get(OrderItem orderItem) {
        PreparedStatement preparedStatement = null;
        try {
            String preparedQuery = "SELECT * FROM Pets.ordersItems WHERE orderId = ? AND productId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, orderItem.getOrderId());
            preparedStatement.setLong(2, orderItem.getProductId());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderItem> resSetCont = parseResultSet(resultSet);
            if (resSetCont.size() == 0) {
                orderItem = null;
            } else {
                orderItem = resSetCont.get(0);
            }
            logger.info("get Order Item");
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting order item from DB " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return orderItem;
    }

    @Override
    public OrderItem findById(Integer id) {
        PreparedStatement preparedStatement = null;
        OrderItem orderItem;
        try {
            String preparedQuery = "SELECT * FROM Pets.ordersItems WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderItem> resSetCont = parseResultSet(resultSet);
            if (resSetCont.size() == 0) {
                orderItem = null;
            } else {
                orderItem = resSetCont.get(0);
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems searching order item by id " + e.getLocalizedMessage());
        } finally {
            closeStatement(preparedStatement, logger);
        }
        return orderItem;
    }

    @Override
    public List<OrderItem> parseResultSet(ResultSet rs) {
        List<OrderItem> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer orderId = rs.getInt("orderId");
                Integer productId = rs.getInt("productId");
                Integer quantity = rs.getInt("quantity");
                BigDecimal price = new BigDecimal(rs.getString("price"));
                OrderItem orderItem = new OrderItem(id, productId, orderId, quantity, price);
                list.add(orderItem);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public void close() {
        logger.info("close connection from OrderItemDAO");
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getLocalizedMessage());
        }
    }
}
