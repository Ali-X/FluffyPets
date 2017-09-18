package com.fluffypets.DAO.orders;

import com.fluffypets.DAO.AbstractDAO;
import com.fluffypets.MVC.model.Order;
import com.fluffypets.MVC.model.OrderItem;
import exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl extends AbstractDAO<OrderItem> implements OrderItemDAO, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(OrderItemDAOImpl.class.getName());

    public OrderItemDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected void createTableIfNotExists() throws DAOException {
        String initialQuery = "CREATE TABLE IF NOT EXISTS `Pets`.`ordersItems` (`id` INT NOT NULL AUTO_INCREMENT," +
                "`orderId` INT NOT NULL,`productId` INT,`quantity` INT NOT NULL,`price` VARCHAR(128) NOT NULL," +
                "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC)," +
                "CONSTRAINT FOREIGN KEY (orderId) REFERENCES orders(id)," +
                "CONSTRAINT FOREIGN KEY (productId) REFERENCES products(id))";
        Statement statement=null;
        try {
            statement = connection.createStatement();
            statement.execute(initialQuery);
            logger.info("createTableIfNotExists query from Order Item Dao");
        } catch (SQLException e) {
            logger.error("Table orderItem creation error\n"+e);
            throw new DAOException("Table orderItem creation error");
        }finally {
            closeStatement(statement,logger);
        }
    }

    @Override
    public List<OrderItem> getAllItems(Integer orderId) {
        List<OrderItem> list = new ArrayList<>();
        OrderItem orderItem;
        Integer id;
        Integer productId;
        Integer quantity;
        BigDecimal price;
            PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "SELECT * FROM Pets.ordersItems WHERE orderId=?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1,orderId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                productId = rs.getInt("productId");
                quantity = rs.getInt("quantity");
                price=new BigDecimal(rs.getString("price"));
                orderItem = new OrderItem(id,productId,orderId,quantity,price);
                list.add(orderItem);
            }
            logger.info("get All order Items query from Order Item Dao");
        } catch (SQLException e) {
            logger.error("get All order Items query error\n"+e);
            throw new DAOException("get All order Items query error");
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return list;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
            PreparedStatement preparedStatement=null;
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
            logger.error("There are problems with new order item insertion to DB\n"+e);
            throw new DAOException("There are problems with new order item insertion to DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
    }

    @Override
    public OrderItem delete(OrderItem orderItem) {
            PreparedStatement preparedStatement=null;
        try {
            String preparedQuery = "DELETE FROM Pets.ordersItems  WHERE orderId = ? AND productId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, orderItem.getOrderId());
            preparedStatement.setLong(2, orderItem.getProductId());
            preparedStatement.execute();
            logger.info("delete Order Item");
        } catch (SQLException e) {
            logger.error("There are problems with order item deleting from DB \n"+e);
            throw new DAOException("There are problems with order item deleting from DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return orderItem;
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        PreparedStatement preparedStatement=null;
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
            logger.error("There are problems with order item update in DB \n"+e);
            throw new DAOException("There are problems with order item update in DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return orderItem;
    }

    @Override
    public OrderItem get(OrderItem orderItem) {
        PreparedStatement preparedStatement=null;
        OrderItem theItem=null;
        try {
            String preparedQuery = "SELECT * FROM Pets.ordersItems WHERE orderId = ? AND productId = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setLong(1, orderItem.getOrderId());
            preparedStatement.setLong(2, orderItem.getProductId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer productId = resultSet.getInt("productId");
                Integer orderId = resultSet.getInt("orderId");
                Integer quantity = resultSet.getInt("quantity");
                BigDecimal price = new BigDecimal(resultSet.getString("price"));
             theItem=new OrderItem(id,productId,orderId,quantity,price);
            }
            logger.info("get Order Item");
        } catch (SQLException e) {
            logger.error("There are problems with getting order item from DB \n"+e);
            throw new DAOException("There are problems with getting order item from DB" + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return theItem;
    }

    @Override
    public OrderItem findById(Integer id) {
        PreparedStatement preparedStatement=null;
        OrderItem orderItem;
        try {
            String preparedQuery = "SELECT * FROM Pets.ordersItems WHERE id = ?";
            preparedStatement = connection.prepareStatement(preparedQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer productId = resultSet.getInt("productId");
                Integer orderId = resultSet.getInt("orderId");
                Integer quantity = resultSet.getInt("quantity");
                BigDecimal price = new BigDecimal(resultSet.getString("price"));
                orderItem=new OrderItem(id,productId,orderId,quantity,price);
                logger.info("find order item by id");
                return orderItem;
            }
        } catch (SQLException e) {
            logger.error("There are problems searching order item by id \n"+e);
            throw new DAOException("There are problems searching order item by id " + e);
        }finally {
            closeStatement(preparedStatement,logger);
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        logger.info("close connection from OrderItemDAO");
        connection.close();
    }
}
