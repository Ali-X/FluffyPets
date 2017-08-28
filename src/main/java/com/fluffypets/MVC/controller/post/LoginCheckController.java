package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.UserService;

public class LoginCheckController implements Controller {
    private UserService userService;

    public LoginCheckController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();
        String userName = request.getAttribute("userName");
        String pass = request.getAttribute("password");
        User user = userService.findUser(userName, pass);
        if (user == null) {
            vm.setView("login");
        } else {
            vm.setAttribute("user", user);
            vm.addCookie("token", user.getToken());
            vm.setView("profile");
        }
        return vm;
    }
}