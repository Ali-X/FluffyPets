package com.fluffypets.dao;

import com.fluffypets.entities.Order;

import java.util.List;

public interface OrderDAO extends GenericDAO<Order> {
    List<Order> getMyOrders(Integer userId);

    List<Order> getAllOrders();
}
