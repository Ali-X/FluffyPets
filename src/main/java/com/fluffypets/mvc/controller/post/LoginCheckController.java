package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.entities.UserAddress;
import com.fluffypets.mvc.page_objects.Cart;
import com.fluffypets.entities.User;
import com.fluffypets.mvc.page_objects.ValidationMessage;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.services.UserDataService;
import com.fluffypets.services.UserService;
import com.fluffypets.validators.SignInValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCheckController implements Controller {
    private static final Logger logger = LogManager.getLogger(LoginCheckController.class.getName());


    private UserService userService;
    private UserDataService userDataService;

    public LoginCheckController(UserService userService, UserDataService userDataService) {
        this.userService = userService;
        this.userDataService = userDataService;
    }

    @Override
    public ViewModel process(Action action, ViewModel vm) {
        String userName = action.getAttribute("userName");
        String pass = action.getAttribute("password");
        Cart myCart = (Cart) vm.getAttribute("cart");

        SignInValidator validator = new SignInValidator();
        ValidationMessage<User> validationMessage = validator.validate(new User(userName, pass));
        User user = userService.findUser(userName, ContextFactory.md5Custom(pass, logger));
        if (user == null) {
            validationMessage.setValidationMessage("user&password pair is absent");
        }

        if (!validationMessage.getValidationMessage().equals("Ok")) {
            vm.setView("login");
            vm.setAttribute("validationUser", validationMessage);
        } else {

            if (myCart != null) {
                myCart.setUser(user);
                vm.setAttribute("cart", myCart);
            }
            vm.setAttribute("user", user);

            UserAddress userAddress = null;
            if (user != null) {
                userAddress = userDataService.get(user.getId());
            }
            if (userAddress != null) vm.setAttribute("userAddress", userAddress);
            vm.setView("home");
        }
        return vm;
    }
}