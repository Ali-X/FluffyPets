package com.fluffypets.DAO.orders;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.MVC.model.Order;
import com.fluffypets.MVC.model.OrderItem;
import exeptions.DAOException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl extends AbstractDAO<OrderItem> implements OrderItemDAO, AutoCloseable {

    public OrderItemDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected void createTableIfNotExists() throws DAOException {
        String initialQuery = "CREATE TABLE IF NOT EXISTS `Pets`.`ordersItems` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`orderId` INT NOT NULL," +
                "`quantity` INT NOT NULL," +
                "`productId` INT NOT NULL," +
                "`price` VARCHAR(128) NOT NULL," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                " CONSTRAINT FOREIGN KEY (orderId) REFERENCES orders(id),"+
                " CONSTRAINT FOREIGN KEY (productId) REFERENCES products(id))";
        try {
            Statement statement = connection.createStatement();
            statement.execute(initialQuery);

        } catch (SQLException e) {
            throw new DAOException("Table orderItem creation error");
        }
    }

    @Override
    public List<OrderItem> getAllItems(Long orderId) {
        List<OrderItem> list = new ArrayList<>();
        OrderItem orderItem;
        Long id;
        Long productId;
        Integer quantity;
        BigDecimal price;
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "SELECT * FROM Pets.ordersItems WHERE orderId=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1,orderId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getLong("id");
                productId = rs.getLong("productId");
                quantity = rs.getInt("quantity");
                price=new BigDecimal(rs.getString("price"));
                orderItem = new OrderItem(id,productId,orderId,quantity,price);
                list.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "INSERT INTO Pets.ordersItems (orderId, productId, quantity, price) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, orderItem.getOrderId());
            preparedStatement.setLong(2, orderItem.getProductId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setString(4, orderItem.getCurrentPrice().toString());
            preparedStatement.execute();
            return get(orderItem);
        } catch (SQLException e) {
            throw new DAOException("There are problems with new order item insertion to DB" + e);
        }
    }

    @Override
    public OrderItem delete(OrderItem orderItem) {
        try {
            PreparedStatement preparedStatement;
            String preparedQuery = "DELETE FROM Pets.ordersItems  WHERE orderId = ? AND productId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, orderItem.getOrderId());
            preparedStatement.setLong(2, orderItem.getProductId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new DAOException("There are problems with order item deleting from DB" + e);
        }
        return orderItem;
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        PreparedStatement preparedStatement;
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
        } catch (SQLException e) {
            throw new DAOException("There are problems with order item update in DB" + e);
        }
        return orderItem;
    }

    @Override
    public OrderItem get(OrderItem orderItem) {
        PreparedStatement preparedStatement;
        OrderItem theItem=null;
        try {
            String preparedQuery = "SELECT * FROM Pets.ordersItems WHERE orderId = ? AND productId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, orderItem.getOrderId());
            preparedStatement.setLong(2, orderItem.getProductId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long productId = resultSet.getLong("productId");
                Long orderId = resultSet.getLong("orderId");
                Integer quantity = resultSet.getInt("quantity");
                BigDecimal price = new BigDecimal(resultSet.getString("price"));
             theItem=new OrderItem(id,productId,orderId,quantity,price);
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems with getting order item from DB" + e);
        }
        return theItem;
    }

    @Override
    public OrderItem findById(Integer id) {
        PreparedStatement preparedStatement;
        OrderItem orderItem;
        try {
            String preparedQuery = "SELECT * FROM Pets.ordersItems WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long productId = resultSet.getLong("productId");
                Long orderId = resultSet.getLong("orderId");
                Integer quantity = resultSet.getInt("quantity");
                BigDecimal price = new BigDecimal(resultSet.getString("price"));
                orderItem=new OrderItem(id,productId,orderId,quantity,price);
                return orderItem;
            }
        } catch (SQLException e) {
            throw new DAOException("There are problems searching order item by id " + e);
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
