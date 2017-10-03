package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.entities.User;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.services.UserService;
import com.fluffypets.exeptions.ServiciesException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordUpdateController implements Controller{
    private static final Logger logger = LogManager.getLogger(PasswordUpdateController.class.getName());

    private UserService userService;

    public PasswordUpdateController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        String email = (String) vm.getAttribute("email");
        String pas1 = command.getAttribute("pas1");
        String pas2 = command.getAttribute("pas2");
        if (pas1.equals(pas2)) {
            User user = userService.findByEmail(email);
            user.setPassword(ContextFactory.md5Custom(pas1, logger));
            userService.update(user);
        } else throw new ServiciesException("different passwords");
        vm.setView("login");
        return vm;
    }
}