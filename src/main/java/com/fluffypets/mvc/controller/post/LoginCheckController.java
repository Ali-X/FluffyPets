package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.Cart;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.model.UserData;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.servicies.user.UserDataService;
import com.fluffypets.servicies.user.UserService;
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
    public ViewModel process(Command command, ViewModel vm) {
        String userName = command.getAttribute("userName");
        String pass = ContextFactory.md5Custom(command.getAttribute("password"), logger);
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
            vm.setView("home");
        }
        return vm;
    }

    @Override
    public void close() throws Exception {
        userDataService.close();
        userService.close();
    }
}