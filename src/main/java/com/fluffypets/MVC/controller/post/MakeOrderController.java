package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Cart;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.model.UserData;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.servicies.user.UserDataService;

public class MakeOrderController implements Controller,AutoCloseable {

    private final UserDataService userDataService;

    public MakeOrderController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }


    @Override
    public ViewModel process(Request request, ViewModel vm) {
        User user = (User) vm.getAttribute("user");
        Cart cart = (Cart) vm.getAttribute("cart");
        if (user == null) {
            vm.setView("login");
            return vm;
        }
        if (cart == null) {
            vm.setView("home");
            return vm;
        }
        UserData userData = userDataService.get(user.getId());
        vm.setAttribute("userData",userData);
        vm.setView("submitOrder");
        return vm;
    }

    @Override
    public void close() throws Exception {
        userDataService.close();
    }
}
