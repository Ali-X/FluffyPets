package com.fluffypets.servicies;

import com.fluffypets.MVC.model.Cart;
import com.fluffypets.MVC.model.Order;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.model.UserData;

import java.time.LocalDate;

public interface OrderService {

    UserData updateAddress(Integer userId, String fullName, String district, String area, String street, String app, String phone);

    Order makeOrder(User user, Cart cart, String comment);

    Order changeOrderStatus(Order order, LocalDate deliveryDate, String comment);

}
