package com.fluffypets.mvc.controller.pages;

import com.fluffypets.dao.user.UserDAO;
import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.factory.ContextFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordRecoveringController implements Controller {

    public PasswordRecoveringController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private UserDAO userDAO;
    private static final Logger logger = LogManager.getLogger(PasswordRecoveringController.class.getName());

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        String email = command.getAttribute("email");
        User user = userDAO.findByEmail(email);
        if (user != null) {
            String who = command.getAttribute("who");
            String verify = command.getAttribute("verify");
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

    @Override
    public void close() throws Exception {
        userDAO.close();
    }
}