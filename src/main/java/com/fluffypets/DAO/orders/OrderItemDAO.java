package com.fluffypets.DAO.orders;

import com.fluffypets.DAO.GenericDAO;
import com.fluffypets.MVC.model.OrderItem;

import java.util.List;

public interface OrderItemDAO extends GenericDAO<OrderItem> {
    List<OrderItem> getAllItems(Integer orderId);
}
