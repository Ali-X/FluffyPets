package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.UserAdress;
import com.fluffypets.mvc.model.page_objects.Cart;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.model.page_objects.ValidationMessage;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.servicies.user.UserDataService;
import com.fluffypets.servicies.user.UserService;
import com.fluffypets.servicies.validators.SignInValidator;
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
    public ViewModel process(Command command, ViewModel vm) {
        String userName = command.getAttribute("userName");
        String pass = command.getAttribute("password");
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

            UserAdress userAdress = null;
            if (user != null) {
                userAdress = userDataService.get(user.getId());
            }
            if (userAdress != null) vm.setAttribute("userAdress", userAdress);
            vm.setView("home");
        }
        return vm;
    }
}