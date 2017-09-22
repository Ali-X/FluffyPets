package com.fluffypets.MVC.controller.pages;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Order;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.servicies.OrderService;

import java.util.List;

public class AdminOrdersPageController implements Controller, AutoCloseable {

    private OrderService orderService;

    public AdminOrdersPageController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ViewModel process(Request request, ViewModel vm) {
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