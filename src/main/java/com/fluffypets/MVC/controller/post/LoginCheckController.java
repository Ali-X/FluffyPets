package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.Cart;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.model.UserData;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.UserDataService;
import com.fluffypets.servicies.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCheckController implements Controller, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(LoginCheckController.class.getName());


    private UserService userService;
    private UserDataService userDataService;

    public LoginCheckController(UserService userService, UserDataService userDataService) {
        this.userService = userService;
        this.userDataService = userDataService;
    }

    @Override
    public ViewModel process(Request request, ViewModel vm) {
        String userName = request.getAttribute("userName");
        String pass = Factory.md5Custom(request.getAttribute("password"),logger);
        Cart myCart = (Cart) vm.getAttribute("cart");
        User user = userService.findUser(userName, pass);
        if (user == null) {
            vm.setView("login");
        } else {
            if (myCart != null) {
                myCart.setUser(user);
                vm.setAttribute("cart", myCart);
            }
            vm.setAttribute("user", user);
            vm.addCookie("FluffyPets", user.getToken());

            UserData userData = userDataService.get(user.getId());
            if (userData != null) vm.setAttribute("userData", userData);
            vm.setView("profile");
        }
        return vm;
    }

    @Override
    public void close() throws Exception {
        userDataService.close();
        userService.close();
    }
}