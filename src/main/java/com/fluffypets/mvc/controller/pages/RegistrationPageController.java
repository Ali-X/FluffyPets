package com.fluffypets.mvc.controller.pages;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationPageController implements Controller {
    private static final Logger logger = LogManager.getLogger(RegistrationPageController.class.getName());

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        vm.setView("signup");
        logger.info("signup page selected");
        return vm;
    }

    @Override
    public void close() throws Exception {

    }
}
