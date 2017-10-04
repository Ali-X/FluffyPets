package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.entities.UserAddress;
import com.fluffypets.mvc.page_objects.Cart;
import com.fluffypets.entities.User;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.UserDataService;

public class MakeOrderController implements Controller {

    private final UserDataService userDataService;

    public MakeOrderController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }


    @Override
    public ViewModel process(Action action, ViewModel vm) {
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
        UserAddress userAddress = userDataService.get(user.getId());
        vm.setAttribute("userAddress", userAddress);
        vm.setView("submitOrder");
        return vm;
    }
}
