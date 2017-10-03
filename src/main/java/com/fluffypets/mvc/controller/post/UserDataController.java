package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.entities.User;
import com.fluffypets.entities.UserAddress;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.UserDataService;

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

            UserAddress userAddress = new UserAddress(user.getId(), fullName, district, area, street, app, phone);

            UserAddress current = userDataService.get(user.getId());
            if (current == null) {
                userAddress = userDataService.create(userAddress);
            } else {
                userAddress.setUserDataId(current.getUserDataId());
                userAddress = userDataService.update(userAddress);
            }
            vm.setAttribute("userAddress", userAddress);
        }
        vm.setView("profile");
        return vm;
    }
}