package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Cart;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.model.UserData;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.OrderService;
import com.fluffypets.servicies.UserDataService;

public class SubmitOrderController implements Controller,AutoCloseable {
    private final OrderService orderService;

    public SubmitOrderController( OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();
        User user = (User) vm.getAttribute("user");
        Cart cart = (Cart) vm.getAttribute("cart");
        UserData userData = (UserData) vm.getAttribute("userData");

        String comment=request.getAttribute("orderComment");
        String fullName=request.getAttribute("Fullname");
        String district=request.getAttribute("District");
        String area=request.getAttribute("Area");
        String street=request.getAttribute("Street");
        String app=request.getAttribute("App");
        String telephone=request.getAttribute("PhoneNumber");

//        orderService.updateAddress(user.getId(),fullName,district,area,street,app,telephone);
        orderService.makeOrder(user,cart,comment);

        vm.removeAttribute("cart");
        vm.removeAttribute("userData");
        vm.setView("thankyou");
        return vm;
    }

    @Override
    public void close() throws Exception {
        orderService.close();
    }
}