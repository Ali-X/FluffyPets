package com.fluffypets.DAO.orders;

import com.fluffypets.DAO.GenericDAO;
import com.fluffypets.MVC.model.Order;

import java.util.List;

public interface OrderDAO extends GenericDAO<Order> {
    List<Order> getMyOrders(Integer userId);
}
