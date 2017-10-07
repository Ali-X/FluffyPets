package com.fluffypets.services.impl;

import com.fluffypets.dao.impl.DaoFactory;
import com.fluffypets.dao.OrderDAO;
import com.fluffypets.dao.UserAddressDAO;
import com.fluffypets.entities.Order;
import com.fluffypets.entities.OrderItem;
import com.fluffypets.entities.User;
import com.fluffypets.entities.UserAddress;
import com.fluffypets.exeptions.DAOException;
import com.fluffypets.exeptions.ServiciesException;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.mvc.page_objects.Cart;
import com.fluffypets.mvc.page_objects.ProductInCart;
import com.fluffypets.services.OrderService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.fluffypets.dao.impl.DaoFactory.getUserAddressDAO;

class OrderServiceImpl implements OrderService {

    @Override
    public UserAddress updateAddress(Integer userId, UserAddress userAddress) {
        Connection connection = ContextFactory.getContextConnection();
        try {
            try {
                connection.setAutoCommit(false);
                UserAddressDAO userAddressDAO = getUserAddressDAO(connection);
                UserAddress userAddressOld = userAddressDAO.getByUserId(userId);
                UserAddress userAddressUpdated;
                if (userAddressOld == null) {
                    userAddressUpdated = new UserAddress(userId, userAddress.getFullName(), userAddress.getDistrict(),
                            userAddress.getArea(),userAddress.getStreet(),userAddress.getApp(), userAddress.getPhone());
                    userAddressUpdated = userAddressDAO.create(userAddressUpdated);
                } else {
                    userAddressUpdated = new UserAddress(userAddressOld.getUserDataId(), userId, userAddress.getFullName(), userAddress.getDistrict(),
                            userAddress.getArea(),userAddress.getStreet(),userAddress.getApp(), userAddress.getPhone());
                    userAddressUpdated = userAddressDAO.update(userAddressUpdated);
                }
                connection.commit();
                return userAddressUpdated;
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
                OrderDAO orderDAO = DaoFactory.getOrderDAO(connection);
                if (cart.getProductInCarts().size() == 0) throw new ServiciesException("empty order");
                if (cart.getTotalPrice().doubleValue() <= 0) throw new ServiciesException("empty order");
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
                OrderDAO orderDAO = DaoFactory.getOrderDAO(connection);
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
                OrderDAO orderDAO = DaoFactory.getOrderDAO(connection);
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
                OrderDAO orderDAO = DaoFactory.getOrderDAO(connection);
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