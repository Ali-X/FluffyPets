package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.UserService;
import exeptions.ServiciesException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordUpdateController implements Controller, AutoCloseable {
    private static final Logger logger = LogManager.getLogger(PasswordUpdateController.class.getName());

    private UserService userService;

    public PasswordUpdateController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request, ViewModel vm) {
        String email = (String) vm.getAttribute("email");
        String pas1=request.getAttribute("pas1");
        String pas2=request.getAttribute("pas2");
        if (pas1.equals(pas2)) {
            User user = userService.findByEmail(email);
            user.setPassword(Factory.md5Custom(pas1,logger));
            userService.update(user);
        }else throw new ServiciesException("different passwords");
        vm.setView("login");
        return vm;
    }

    @Override
    public void close() throws Exception {
        userService.close();
    }
}