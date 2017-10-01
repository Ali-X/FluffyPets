package com.fluffypets.servicies.order;

import com.fluffypets.mvc.model.page_objects.Cart;
import com.fluffypets.mvc.model.Order;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.model.UserAdress;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    UserAdress updateAddress(Integer userId, String fullName, String district, String area, String street, String app, String phone);

    Order makeOrder(User user, Cart cart, String comment);

    Order changeOrderStatus(Integer orderId, LocalDate deliveryDate, String status);

    List<Order> getAllOrders();

    boolean delete(Integer orderId);
}
