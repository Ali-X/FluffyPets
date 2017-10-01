package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.Cart;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.model.UserData;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.servicies.user.UserDataService;

public class MakeOrderController implements Controller {

    private final UserDataService userDataService;

    public MakeOrderController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }


    @Override
    public ViewModel process(Command command, ViewModel vm) {
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
        vm.setAttribute("userData", userData);
        vm.setView("submitOrder");
        return vm;
    }
}
