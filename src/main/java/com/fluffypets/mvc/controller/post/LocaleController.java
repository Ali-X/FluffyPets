package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.enumes.Prices;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleController implements Controller {

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        String locale = command.getAttribute("locale");
        String page = command.getAttribute("page");
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

        localeTotal(vm);

        vm.setView(page);
        return vm;
    }

    private void localeTotal(ViewModel vm) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("language", vm.getCurrentLocale());
        vm.setAttribute("prices", Prices.values());
        vm.setAttribute("Add_to_cart", ViewModel.stringUTF8(resourceBundle.getString("Add_to_cart")));
        vm.setAttribute("Admin_page", ViewModel.stringUTF8(resourceBundle.getString("Admin_page")));
        vm.setAttribute("Confirm_your_order", ViewModel.stringUTF8(resourceBundle.getString("Confirm_your_order")));
        vm.setAttribute("Create_product", ViewModel.stringUTF8(resourceBundle.getString("Create_product")));
        vm.setAttribute("Logout", ViewModel.stringUTF8(resourceBundle.getString("Logout")));
        vm.setAttribute("My_cart", ViewModel.stringUTF8(resourceBundle.getString("My_cart")));
        vm.setAttribute("My_profile", ViewModel.stringUTF8(resourceBundle.getString("My_profile")));
        vm.setAttribute("Products", ViewModel.stringUTF8(resourceBundle.getString("Products")));
        vm.setAttribute("Select_categories", ViewModel.stringUTF8(resourceBundle.getString("Select_categories")));
        vm.setAttribute("Select_price_range", ViewModel.stringUTF8(resourceBundle.getString("Select_price_range")));
        vm.setAttribute("Signin", ViewModel.stringUTF8(resourceBundle.getString("Signin")));
        vm.setAttribute("Signup", ViewModel.stringUTF8(resourceBundle.getString("Signup")));
        vm.setAttribute("Welcome", ViewModel.stringUTF8(resourceBundle.getString("Welcome")));
        vm.setAttribute("message_L", ViewModel.stringUTF8(resourceBundle.getString("message_L")));
        vm.setAttribute("All", ViewModel.stringUTF8(resourceBundle.getString("All")));
        vm.setAttribute("Language", ViewModel.stringUTF8(resourceBundle.getString("Language")));
        vm.setAttribute("Select", ViewModel.stringUTF8(resourceBundle.getString("Select")));
        vm.setAttribute("OrderLabel", ViewModel.stringUTF8(resourceBundle.getString("OrderLabel")));
        vm.setAttribute("IncreasePrice", ViewModel.stringUTF8(resourceBundle.getString("IncreasePrice")));
        vm.setAttribute("DecreasePrice", ViewModel.stringUTF8(resourceBundle.getString("DecreasePrice")));
    }
}