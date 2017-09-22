package com.fluffypets.MVC.controller.pages;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditUserProfileController implements Controller {
    private static final Logger logger = LogManager.getLogger(EditUserProfileController.class.getName());

    @Override
    public ViewModel process(Request request,ViewModel vm) {
        vm.setView("editProfile");
        logger.info("editProfile page selected");
        return vm;
    }

    @Override
    public void close() throws Exception {

    }
}
