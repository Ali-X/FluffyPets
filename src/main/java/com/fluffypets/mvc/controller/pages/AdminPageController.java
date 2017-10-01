package com.fluffypets.mvc.controller.pages;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.servicies.user.UserService;

import java.util.List;

public class AdminPageController implements Controller {

    private UserService userService;

    public AdminPageController(UserService userService){this.userService=userService;}

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        List<User> users= userService.getAllUsers();
            vm.setAttribute("users", users);
            vm.setView("admin");
        return vm;
    }
}
