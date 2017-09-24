package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.servicies.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpCheckController implements Controller, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(SignUpCheckController.class.getName());

    private UserService userService;

    public SignUpCheckController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request, ViewModel vm) {
        String userName = request.getAttribute("form-userName");
        String email = request.getAttribute("form-email");
        String password = ContextFactory.md5Custom(request.getAttribute("form-password"),logger);
        String token = ContextFactory.md5Custom(userName, logger);
        String role = "user";
        User user = new User(1, userName, password, email, role);
        user = userService.create(user);
        vm.setAttribute("user", user);
        vm.addCookie("FluffyPets", user.getToken());
        vm.setView("profile");
        return vm;
    }

    @Override
    public void close() throws Exception {
        userService.close();
    }
}
