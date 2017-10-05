package com.fluffypets.mvc.controller.pages;

import com.fluffypets.entities.User;
import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.page_objects.Cart;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class LogoutPageController implements Controller {
    private static final Logger logger = LogManager.getLogger(LogoutPageController.class.getName());

    @Override
    public ViewModel process(Action action, ViewModel vm) {
        User user = new User(0, "SignOut", "", "", "user");
        Cart cart = new Cart(user);

        vm.setAttribute("user",user);
        vm.setAttribute("cart",cart);
        vm.setView("login");
        logger.info("logout page selected");
        return vm;
    }
}

