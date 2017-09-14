package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.UserService;

import java.util.List;

public class EditUserRoleController implements Controller {
    private UserService userService;

    public EditUserRoleController(UserService userService){this.userService=userService;}
    @Override
    public ViewModel process(Request request) {
        Integer userId=Integer.valueOf(request.getAttribute("userId"));
        String command=request.getAttribute("command");
        userService.changeRole(userId,command);
        List<User> users= userService.getAllUsers();
        ViewModel vm=Factory.getViewModel();
        vm.setAttribute("users", users);
        vm.setView("admin");
        return vm;
    }
}
