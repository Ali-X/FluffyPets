package com.fluffypets.dao.orders;

import com.fluffypets.dao.GenericDAO;
import com.fluffypets.mvc.model.Order;

import java.util.List;

public interface OrderDAO extends GenericDAO<Order> {
    List<Order> getMyOrders(Integer userId);

    List<Order> getAllOrders();
}
