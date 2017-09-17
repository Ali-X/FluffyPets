package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Cart;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.model.UserData;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.UserDataService;

public class MakeOrderController implements Controller {

    private final UserDataService userDataService;

    public MakeOrderController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }


    @Override
    public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();
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
}