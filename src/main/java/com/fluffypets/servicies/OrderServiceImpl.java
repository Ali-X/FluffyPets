package com.fluffypets.servicies;

import com.fluffypets.DAO.orders.OrderDAO;
import com.fluffypets.DAO.user.UserDataDAO;
import com.fluffypets.MVC.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService,AutoCloseable {
    private UserDataDAO userDataDAO;
    private OrderDAO orderDAO;

    public OrderServiceImpl(UserDataDAO userDataDAO, OrderDAO orderDAO) {
        this.userDataDAO = userDataDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public UserData updateAddress(Integer userId, String fullName, String district, String area, String street, String app, String phone) {
        UserData userData = userDataDAO.getByUserId(userId);

        UserData userDataUpdated = new UserData(userId, fullName, null, "Male", false, district, area, street, app, phone, null);
        if (userData == null) {
            userDataDAO.create(userDataUpdated);
        }
        return userDataUpdated;
    }

    @Override
    public Order makeOrder(User user, Cart cart, String comment) {
        if (cart.getProductInCarts().size()==0)throw new RuntimeException("empty order");
        if (cart.getTotalPrice().doubleValue()<=0)throw new RuntimeException("empty order");
        List<OrderItem> orderItems=cart.getProductInCarts().stream().
                map(ProductInCart::toOrderItem).collect(Collectors.toList());
        Order myOrder=new Order(user.getId(),LocalDate.now(),LocalDate.now().plusMonths(1).plusDays(1),"new order",orderItems,comment);
        myOrder=orderDAO.create(myOrder);
        return myOrder;
    }

    @Override
    public Order changeOrderStatus(Integer orderId, LocalDate deliveryDate, String status) {
        Order thisOrder=orderDAO.findById(orderId);
        thisOrder.setDeliveryDate(deliveryDate);
        thisOrder.setStatus(status);
        thisOrder=orderDAO.update(thisOrder);
        return thisOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public void close() throws Exception {
        userDataDAO.close();
        orderDAO.close();
    }
}