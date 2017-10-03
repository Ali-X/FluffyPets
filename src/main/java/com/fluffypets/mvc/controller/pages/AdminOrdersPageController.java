package com.fluffypets.mvc.controller.pages;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.entities.Order;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.OrderService;

import java.util.List;

public class AdminOrdersPageController implements Controller {

    private OrderService orderService;

    public AdminOrdersPageController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        List<Order> orders = orderService.getAllOrders();
        vm.setAttribute("orders", orders);
        vm.setView("adminOrders");
        return vm;
    }
}