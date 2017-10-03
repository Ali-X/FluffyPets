package com.fluffypets.dao;

import com.fluffypets.entities.OrderItem;

import java.util.List;

public interface OrderItemDAO extends GenericDAO<OrderItem> {
    List<OrderItem> getAllItems(Integer orderId);
}
