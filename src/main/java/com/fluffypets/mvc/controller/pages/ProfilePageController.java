package com.fluffypets.mvc.controller.pages;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProfilePageController implements Controller {
    private static final Logger logger = LogManager.getLogger(ProfilePageController.class.getName());

    @Override
    public ViewModel process(Action action, ViewModel vm) {
        vm.setView("profile");
        logger.info("profile page selected");
        return vm;
    }
}
