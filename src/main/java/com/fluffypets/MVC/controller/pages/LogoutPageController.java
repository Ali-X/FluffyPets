package com.fluffypets.MVC.controller.pages;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class LogoutPageController implements Controller {
    private static final Logger logger = LogManager.getLogger(LogoutPageController.class.getName());

    @Override
    public ViewModel process(Request request, ViewModel vm) {
        vm.setAttributes(new HashMap<>());
        vm.setView("login");
        logger.info("logout page selected");
        return vm;
    }

    @Override
    public void close() throws Exception {

    }
}

