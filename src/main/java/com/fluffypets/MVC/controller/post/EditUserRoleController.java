package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.servicies.UserService;

import java.util.List;

public class EditUserRoleController implements Controller,AutoCloseable {
    private UserService userService;

    public EditUserRoleController(UserService userService){this.userService=userService;}
    @Override
    public ViewModel process(Request request, ViewModel vm) {
        Integer userId=Integer.valueOf(request.getAttribute("userId"));
        String command=request.getAttribute("command");
        userService.changeRole(userId,command);
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
