package com.fluffypets.mvc.controller.pages;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPageController implements Controller {
    private static final Logger logger = LogManager.getLogger(LoginPageController.class.getName());

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        vm.setView("login");
        logger.info("login page selected");
        return vm;
    }
}
