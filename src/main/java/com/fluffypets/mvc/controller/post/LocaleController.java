package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;

import java.util.Locale;

public class LocaleController implements Controller {

    @Override
    public ViewModel process(Action action, ViewModel vm) {
        String locale = action.getAttribute("locale");
        String page = action.getAttribute("page");
        switch (locale) {
            case "en_US":
                vm.setCurrentLocale(new Locale("en", "US"));
                vm.setAttribute("isUa", "false");
                break;
            case "uk_UA":
                vm.setCurrentLocale(new Locale("uk", "UA"));
                vm.setAttribute("isUa", "true");
                break;
            default:
                vm.setCurrentLocale(new Locale("en", "US"));
                vm.setAttribute("isUa", "false");
        }
        vm.setView(page);
        return vm;
    }

}