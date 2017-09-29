package com.fluffypets.servicies.order;

import com.fluffypets.mvc.model.Cart;
import com.fluffypets.mvc.model.Order;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.model.UserData;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    UserData updateAddress(Integer userId, String fullName, String district, String area, String street, String app, String phone);

    Order makeOrder(User user, Cart cart, String comment);

    Order changeOrderStatus(Integer orderId, LocalDate deliveryDate, String status);

    List<Order> getAllOrders();

    void close() throws Exception;

    boolean delete(Integer orderId);
}
