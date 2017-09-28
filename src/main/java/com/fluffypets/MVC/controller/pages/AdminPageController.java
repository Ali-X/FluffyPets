package com.fluffypets.MVC.controller.pages;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.servicies.user.UserService;

import java.util.List;

public class AdminPageController implements Controller,AutoCloseable {

    private UserService userService;

    public AdminPageController(UserService userService){this.userService=userService;}

    @Override
    public ViewModel process(Request request,ViewModel vm) {
        List<User> users= userService.getAllUsers();
            vm.setAttribute("users", users);
            vm.setView("admin");
        return vm;
    }

    @Override
    public void close() throws Exception {
        userService.close();
    }
}
