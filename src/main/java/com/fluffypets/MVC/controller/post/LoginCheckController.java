package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.model.UserData;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.UserDataService;
import com.fluffypets.servicies.UserService;

public class LoginCheckController implements Controller {
    private UserService userService;
    private UserDataService userDataService;

    public LoginCheckController(UserService userService, UserDataService userDataService) {
        this.userService = userService;
        this.userDataService=userDataService;
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

            UserData userData=userDataService.get(user.getId().longValue());
            if (userData!=null)vm.setAttribute("userData",userData);
            vm.setView("profile");
        }
        return vm;
    }
}