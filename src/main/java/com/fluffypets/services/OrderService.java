package com.fluffypets.services;

import com.fluffypets.mvc.page_objects.Cart;
import com.fluffypets.entities.Order;
import com.fluffypets.entities.User;
import com.fluffypets.entities.UserAddress;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    UserAddress updateAddress(Integer userId, String fullName, String district, String area, String street, String app, String phone);

    Order makeOrder(User user, Cart cart, String comment);

    Order changeOrderStatus(Integer orderId, LocalDate deliveryDate, String status);

    List<Order> getAllOrders();

    boolean delete(Integer orderId);
}
