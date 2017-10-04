package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.entities.User;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.UserService;

import java.util.List;

public class EditUserRoleController implements Controller {
    private UserService userService;

    public EditUserRoleController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Action request, ViewModel vm) {
        Integer userId = Integer.valueOf(request.getAttribute("userId"));
        String command = request.getAttribute("command");
        userService.changeRole(userId, command);
        List<User> users = userService.getAllUsers();
        vm.setAttribute("users", users);
        vm.setView("admin");
        return vm;
    }
}
