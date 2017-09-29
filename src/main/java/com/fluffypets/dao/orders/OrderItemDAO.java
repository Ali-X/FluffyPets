package com.fluffypets.dao.orders;

import com.fluffypets.dao.GenericDAO;
import com.fluffypets.mvc.model.OrderItem;

import java.util.List;

public interface OrderItemDAO extends GenericDAO<OrderItem> {
    List<OrderItem> getAllItems(Integer orderId);
}
