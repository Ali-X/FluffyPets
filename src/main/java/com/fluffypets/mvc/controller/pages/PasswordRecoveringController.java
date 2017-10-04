package com.fluffypets.mvc.controller.pages;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.entities.User;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.services.UserService;
import com.fluffypets.services.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordRecoveringController implements Controller {

    private static final Logger logger = LogManager.getLogger(PasswordRecoveringController.class.getName());

    @Override
    public ViewModel process(Action action, ViewModel vm) {
        String email = action.getAttribute("email");
        UserService userService=new UserServiceImpl();
        User user = userService.findByEmail(email);
        if (user != null) {
            String who = action.getAttribute("who");
            String verify = action.getAttribute("verify");
            if (who.equals(ContextFactory.md5Custom(user.getId().toString(), logger))) {
                if (verify.equals(ContextFactory.md5Custom(user.getPassword() + user.getRoleString(), logger))) {
                    vm.setAttribute("email", email);
                    vm.setView("recover");
                } else throw new SecurityException("Illegal access");
            } else throw new SecurityException("Illegal access");
            vm.setAttribute("login", user.getUserName());
        } else throw new SecurityException("Illegal access");
        return vm;
    }
}