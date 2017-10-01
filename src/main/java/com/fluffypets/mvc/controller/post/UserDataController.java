package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.model.UserData;
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
            LocalDate localDate = LocalDate.parse(command.getAttribute("DateOfBirth"));
            String gender = command.getAttribute("Gender");
            Boolean married = command.getAttribute("Marital").equals("true");
            String district = command.getAttribute("District");
            String area = command.getAttribute("Area");
            String street = command.getAttribute("Street");
            String app = command.getAttribute("App");

            String prim = command.getAttribute("Phone number");
            String second = command.getAttribute("PhoneSecNumber");

            UserData userData = new UserData(user.getId(), fullName, localDate, gender, married, district,
                    area, street, app, prim, second);

            UserData current = userDataService.get(user.getId());
            if (current == null) {
                userData = userDataService.create(userData);
            } else {
                userData.setUserDataId(current.getUserDataId());
                userData = userDataService.update(userData);
            }
            vm.setAttribute("userData", userData);
        }
        vm.setView("profile");
        return vm;
    }
}