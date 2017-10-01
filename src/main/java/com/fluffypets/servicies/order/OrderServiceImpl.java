package com.fluffypets.servicies.order;

import com.fluffypets.dao.orders.OrderDAO;
import com.fluffypets.dao.orders.OrderDAOImpl;
import com.fluffypets.dao.user.UserDataDAO;
import com.fluffypets.dao.user.UserDataDAOImpl;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.mvc.model.*;
import com.fluffypets.mvc.model.page_objects.Cart;
import com.fluffypets.mvc.model.page_objects.ProductInCart;
import exeptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    @Override
    public UserAdress updateAddress(Integer userId, String fullName, String district, String area, String street, String app, String phone) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserDataDAO userDataDAO = new UserDataDAOImpl(connection);
                UserAdress userAdress = userDataDAO.getByUserId(userId);
                UserAdress userAdressUpdated;
                if (userAdress == null) {
                    userAdressUpdated = new UserAdress(userId, fullName, district, area, street, app, phone);
                    userAdressUpdated = userDataDAO.create(userAdressUpdated);
                } else {
                    userAdressUpdated = new UserAdress(userAdress.getUserDataId(), userId, fullName, district, area, street, app, phone);
                    userAdressUpdated = userDataDAO.update(userAdressUpdated);
                }
                connection.commit();
                return userAdressUpdated;
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Transaction error " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Order makeOrder(User user, Cart cart, String comment) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                OrderDAO orderDAO = new OrderDAOImpl(connection);
                if (cart.getProductInCarts().size() == 0) throw new RuntimeException("empty order");
                if (cart.getTotalPrice().doubleValue() <= 0) throw new RuntimeException("empty order");
                List<OrderItem> orderItems = cart.getProductInCarts().stream().
                        map(ProductInCart::toOrderItem).collect(Collectors.toList());
                Order myOrder = new Order(user.getId(), LocalDate.now(), LocalDate.now().plusMonths(1).plusDays(1), "new order", orderItems, comment);
                myOrder = orderDAO.create(myOrder);
                connection.commit();
                return myOrder;
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Transaction error " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Order changeOrderStatus(Integer orderId, LocalDate deliveryDate, String status) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                OrderDAO orderDAO = new OrderDAOImpl(connection);
                Order thisOrder = orderDAO.findById(orderId);
                thisOrder.setDeliveryDate(deliveryDate);
                thisOrder.setStatus(status);
                thisOrder = orderDAO.update(thisOrder);
                connection.commit();
                return thisOrder;
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Transaction error " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                OrderDAO orderDAO = new OrderDAOImpl(connection);
                List<Order> orders = orderDAO.getAllOrders();
                connection.commit();
                return orders;
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Transaction error " + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer orderId) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                OrderDAO orderDAO = new OrderDAOImpl(connection);
                Order order = orderDAO.findById(orderId);
                Order theOrder=orderDAO.delete(order);
                connection.commit();
                return theOrder != null;
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Transaction error " + e.getLocalizedMessage());
        }
        return false;
    }
}