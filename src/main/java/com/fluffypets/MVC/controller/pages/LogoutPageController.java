package com.fluffypets.MVC.controller.pages;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoutPageController implements Controller {
    private static final Logger logger = LogManager.getLogger(LogoutPageController.class.getName());

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();
        vm.removeAttribute("user");
        vm.removeAttribute("token");
        vm.setView("login");
        logger.info("logout page selected");
        return vm;
    }
}

