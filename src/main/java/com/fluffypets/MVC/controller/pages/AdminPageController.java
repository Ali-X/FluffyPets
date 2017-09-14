package com.fluffypets.MVC.controller.pages;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.controller.post.LoginCheckController;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AdminPageController implements Controller {
    private static final Logger logger = LogManager.getLogger(AdminPageController.class.getName());

    private UserService userService;

    public AdminPageController(UserService userService){this.userService=userService;}

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();
        List<User> users= userService.getAllUsers();
            vm.setAttribute("users", users);
            vm.setView("admin");
        return vm;
    }
}
