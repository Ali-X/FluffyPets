package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.servicies.user.UserService;

import java.util.List;

public class EditUserRoleController implements Controller, AutoCloseable {
    private UserService userService;

    public EditUserRoleController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Command request, ViewModel vm) {
        Integer userId = Integer.valueOf(request.getAttribute("userId"));
        String command = request.getAttribute("command");
        userService.changeRole(userId, command);
        List<User> users = userService.getAllUsers();
        vm.setAttribute("users", users);
        vm.setView("admin");
        return vm;
    }

    @Override
    public void close() throws Exception {
        userService.close();
    }
}
