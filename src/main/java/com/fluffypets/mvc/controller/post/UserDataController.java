package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.entities.User;
import com.fluffypets.entities.UserAddress;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.UserDataService;

public class UserDataController implements Controller {

    private UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    public ViewModel process(Action action, ViewModel vm) {
        User user = (User) vm.getAttribute("user");
        if (user == null) {
            vm.setView("login");
        } else {
            String fullName = action.getAttribute("Fullname");
            String district = action.getAttribute("District");
            String area = action.getAttribute("Area");
            String street = action.getAttribute("Street");
            String app = action.getAttribute("App");

            String phone = action.getAttribute("Phone number");

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