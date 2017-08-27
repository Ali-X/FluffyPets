package com.fluffypets.MVC.controller.pages;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;

public class ProfilePageController implements Controller {
    @Override
    public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();
        vm.setView("profile");
        return vm;
    }
}
