package com.fluffypets.DAO.orders;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.MVC.model.Order;
import com.fluffypets.MVC.model.OrderItem;
import exeptions.DAOException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO, AutoCloseable {

    private OrderItemDAO itemDAO;

    public OrderDAOImpl(Connection connection) {
        super(connection);
        itemDAO=new OrderItemDAOImpl(connection);
    }

    @Override
    protected void createTableIfNotExists() throws DAOException {
        String initialQuery = "CREATE TABLE IF NOT EXISTS `Pets`.`orders` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`userId` INT NOT NULL," +
                "`dateOfOrder` DATE NOT NULL," +
                "`orderStatus` VARCHAR(16) NOT NULL," +
                "`dateOfDelivery` DATE," +
                "`comment` VARCHAR(128)," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                " CONSTRAINT FOREIGN KEY (userId) REFERENCES users(id))";
        try {
            Statement statement = connection.createStatement();
            statement.execute(initialQuery);

        } catch (SQLException e) {
            throw new DAOException("Table order creation error");
        }
    }


    @Override
    public List<Order> getMyOrders(Long userId) {
        List<Order> list = new ArrayList<>();

        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "SELECT * FROM Pets.orders WHERE userId=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                LocalDate dateOfOrder= resultSet.getDate("dateOfOrder").toLocalDate();
                String orderStatus= resultSet.getString("orderStatus");
                LocalDate dateOfDelivery= resultSet.getDate("dateOfDelivery").toLocalDate();
                String comment= resultSet.getString("comment");
                List<OrderItem> items=itemDAO.getAllItems(id);
                list.add(new Order(id,userId,dateOfOrder,dateOfDelivery,orderStatus,items,comment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Order create(Order order) {
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "INSERT INTO Pets.orders (userId, dateOfOrder, orderStatus, dateOfDelivery, comment) VALUES(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setDate(2, Date.valueOf(order.getOrderDate()));
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setDate(4, Date.valueOf(order.getDeliveryDate()));
            preparedStatement.setString(5, order.getComment());
            preparedStatement.execute();
            return get(order);
        } catch (SQLException e) {
            throw new DAOException("There are problems with new order insertion to DB" + e);
        }
    }

    @Override
    public Order delete(Order order) {
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "DELETE FROM Pets.orders  WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, order.getOrderId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("There are problems with order deleting from DB" + e);
        }
        return order;
    }

    @Override
    public Order update(Order order) {
        PreparedStatement preparedStatement;
        try {
            String preparedQuery = "UPDATE Pets.orders SET  userId = ?,dateOfOrder = ?, orderStatus = ?, dateOfDelivery = ?, comment=? WHERE id =?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setDate(2, Date.valueOf(order.getOrderDate()));
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setDate(4, Date.valueOf(order.getDeliveryDate()));
            preparedStatement.setString(5, order.getComment());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("There are problems with order item update in DB" + e);
        }
        return order;
    }

    @Override
    public Order get(Order order) {
        PreparedStatement preparedStatement;
        Order theOrder=null;
        try {
            String preparedQuery = "SELECT * FROM Pets.orders WHERE userId = ? AND dateOfOrder = ? AND orderStatus = ?" +
                    "AND dateOfDelivery = ? AND comment = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setDate(2, Date.valueOf(order.getOrderDate()));
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setDate(4, Date.valueOf(order.getDeliveryDate()));
            preparedStatement.setString(5, order.getComment());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                List<OrderItem> items=itemDAO.getAllItems(id);
                theOrder=new Order(id,order.getUserId(),order.getOrderDate(),
                        order.getDeliveryDate(),order.getStatus(),items,order.getComment());

                return theOrder;
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting orders from DB" + e);
        }
        return null;
    }

    @Override
    public Order findById(Integer id) {
        PreparedStatement preparedStatement;
        Order theOrder=null;
        try {
            String preparedQuery = "SELECT * FROM Pets.orders WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long userId = resultSet.getLong("userId");
                LocalDate dateOfOrder= resultSet.getDate("dateOfOrder").toLocalDate();
                String orderStatus= resultSet.getString("orderStatus");
                LocalDate dateOfDelivery= resultSet.getDate("dateOfDelivery").toLocalDate();
                String comment= resultSet.getString("comment");
                List<OrderItem> items=itemDAO.getAllItems(id.longValue());
                theOrder=new Order(id,userId,dateOfOrder,dateOfDelivery,orderStatus,items,comment);

                return theOrder;
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting orders from DB" + e);
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
