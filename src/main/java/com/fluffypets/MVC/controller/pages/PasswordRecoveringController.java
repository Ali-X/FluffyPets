package com.fluffypets.MVC.controller.pages;

import com.fluffypets.DAO.user.UserDAO;
import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.model.User;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordRecoveringController implements Controller {

    public PasswordRecoveringController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private UserDAO userDAO;
    private static final Logger logger = LogManager.getLogger(PasswordRecoveringController.class.getName());

    @Override
    public ViewModel process(Request request, ViewModel vm) {
        String email = request.getAttribute("email");
        User user = userDAO.findByEmail(email);
        if (user != null) {
            String who = request.getAttribute("who");
            String verify = request.getAttribute("verify");
            if (who.equals(Factory.md5Custom(user.getId().toString(), logger))) {
                if (verify.equals(Factory.md5Custom(user.getPassword() + user.getRoleString(), logger))) {
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