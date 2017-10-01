package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.model.UserAdress;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.servicies.user.UserDataService;

import java.time.LocalDate;

public class UserDataController implements Controller {

    private UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        User user = (User) vm.getAttribute("user");
        if (user == null) {
            vm.setView("login");
        } else {
            String fullName = command.getAttribute("Fullname");
            String district = command.getAttribute("District");
            String area = command.getAttribute("Area");
            String street = command.getAttribute("Street");
            String app = command.getAttribute("App");

            String phone = command.getAttribute("Phone number");

            UserAdress userAdress = new UserAdress(user.getId(), fullName, district, area, street, app, phone);

            UserAdress current = userDataService.get(user.getId());
            if (current == null) {
                userAdress = userDataService.create(userAdress);
            } else {
                userAdress.setUserDataId(current.getUserDataId());
                userAdress = userDataService.update(userAdress);
            }
            vm.setAttribute("userAdress", userAdress);
        }
        vm.setView("profile");
        return vm;
    }
}