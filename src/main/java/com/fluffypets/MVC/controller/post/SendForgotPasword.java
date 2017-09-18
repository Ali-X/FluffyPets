package com.fluffypets.MVC.controller.post;

import com.fluffypets.MVC.controller.Controller;
import com.fluffypets.MVC.servlets.Request;
import com.fluffypets.MVC.servlets.ViewModel;
import com.fluffypets.factory.Factory;
import com.fluffypets.servicies.UserDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendForgotPasword implements Controller,AutoCloseable {               // TODO: 9/2/17 demands implementation
    private static final Logger logger = LogManager.getLogger(SendForgotPasword.class.getName());

    private UserDataService userDataService;

    public SendForgotPasword(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    public ViewModel process(Request request) {
        ViewModel vm = Factory.getViewModel();
//        User user = (User) vm.getAttribute("user");
//        if (user == null) {
//            vm.setView("login");
//        } else {
//            String fullName = request.getAttribute("Fullname");
//            LocalDate localDate = LocalDate.parse(request.getAttribute("DateOfBirth"));
//            String gender = request.getAttribute("Gender");
//            Boolean married = request.getAttribute("Marital").equals("true");
//            String district = request.getAttribute("District");
//            String area = request.getAttribute("Area");
//            String street = request.getAttribute("Street");
//            String app = request.getAttribute("App");
//
//            String prim = request.getAttribute("Phone number");
//            String second = request.getAttribute("PhoneSecNumber");
//
//            UserData userData = new UserData(user.getId().longValue(), fullName, localDate, gender, married, district,
//                    area, street, app, prim, second);
//
//            UserData current = userDataService.get(user.getId().longValue());
//            if (current == null) {
//                userData = userDataService.create(userData);
//            } else {
//                userData.setUserDataId(current.getUserDataId());
//                userData = userDataService.update(userData);
//            }
//            vm.setAttribute("userData", userData);
//        }
        vm.setView("profile");
        return vm;
    }

    @Override
    public void close() throws Exception {
        userDataService.close();
    }
}
