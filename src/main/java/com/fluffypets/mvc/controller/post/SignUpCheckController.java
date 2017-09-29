package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.servicies.user.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpCheckController implements Controller, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(SignUpCheckController.class.getName());

    private UserService userService;

    public SignUpCheckController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        String userName = command.getAttribute("form-userName");
        String email = command.getAttribute("form-email");
        String password = ContextFactory.md5Custom(command.getAttribute("form-password"), logger);
        String role = "user";
        User user = new User(1, userName, password, email, role);
        user = userService.create(user);
        vm.setAttribute("user", user);
        vm.addCookie("FluffyPets", user.getToken());
        vm.setView("home");
        return vm;
    }

    @Override
    public void close() throws Exception {
        userService.close();
    }
}
