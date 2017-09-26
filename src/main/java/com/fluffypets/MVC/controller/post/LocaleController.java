package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleController implements Controller,AutoCloseable {

    @Override
    public ViewModel process(Request request, ViewModel vm) {
        String locale=request.getAttribute("locale");
        String page=request.getAttribute("page");
        switch (locale){
            case "en_US": vm.setCurrentLocale(new Locale("en","US"));
            break;
            case "uk_UA": vm.setCurrentLocale(new Locale("uk","UA"));
            break;
            default:vm.setCurrentLocale(new Locale("en","US"));
        }
        //        -------------         localization of all pages        ----------------------
        ResourceBundle resourceBundle = ResourceBundle.getBundle("language", vm.getCurrentLocale());
        vm.setAttribute("Add_to_cart",ViewModel.stringUTF8(resourceBundle.getString("Add_to_cart")));
        vm.setAttribute("Admin_page",ViewModel.stringUTF8(resourceBundle.getString("Admin_page")));
        vm.setAttribute("Confirm_your_order",ViewModel.stringUTF8(resourceBundle.getString("Confirm_your_order")));
        vm.setAttribute("Create_product",ViewModel.stringUTF8(resourceBundle.getString("Create_product")));
        vm.setAttribute("Logout",ViewModel.stringUTF8(resourceBundle.getString("Logout")));
        vm.setAttribute("My_cart",ViewModel.stringUTF8(resourceBundle.getString("My_cart")));
        vm.setAttribute("My_profile",ViewModel.stringUTF8(resourceBundle.getString("My_profile")));
        vm.setAttribute("Products",ViewModel.stringUTF8(resourceBundle.getString("Products")));
        vm.setAttribute("Select_categories",ViewModel.stringUTF8(resourceBundle.getString("Select_categories")));
        vm.setAttribute("Select_price_range",ViewModel.stringUTF8(resourceBundle.getString("Select_price_range")));
        vm.setAttribute("Signin",ViewModel.stringUTF8(resourceBundle.getString("Signin")));
        vm.setAttribute("Signup",ViewModel.stringUTF8(resourceBundle.getString("Signup")));
        vm.setAttribute("Welcome",ViewModel.stringUTF8(resourceBundle.getString("Welcome")));
        vm.setAttribute("message_L",ViewModel.stringUTF8(resourceBundle.getString("message_L")));
        vm.setAttribute("All",ViewModel.stringUTF8(resourceBundle.getString("All")));
        vm.setAttribute("Language",ViewModel.stringUTF8(resourceBundle.getString("Language")));
        vm.setAttribute("Select",ViewModel.stringUTF8(resourceBundle.getString("Select")));
        vm.setAttribute("OrderLabel", ViewModel.stringUTF8(resourceBundle.getString("OrderLabel")));
        vm.setAttribute("IncreasePrice", ViewModel.stringUTF8(resourceBundle.getString("IncreasePrice")));
        vm.setAttribute("DecreasePrice", ViewModel.stringUTF8(resourceBundle.getString("DecreasePrice")));
//        =============         localization        ======================

        vm.setView(page);
        return vm;
    }

    @Override
    public void close() throws Exception {
    }
}