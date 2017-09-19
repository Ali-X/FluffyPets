package com.fluffypets.MVC.controller.pages;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Order;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.OrderService;
import com.fluffypets.servicies.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AdminOrdersPageController implements Controller, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(AdminOrdersPageController.class.getName());

    private OrderService orderService;

    public AdminOrdersPageController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();
        List<Order> orders = orderService.getAllOrders();
        vm.setAttribute("orders", orders);
        vm.setView("adminOrders");
        return vm;
    }

    @Override
    public void close() throws Exception {
        orderService.close();
    }
}