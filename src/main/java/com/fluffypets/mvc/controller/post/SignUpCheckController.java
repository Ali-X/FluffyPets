package com.fluffypets.mvc.controller.post;

import com.fluffypets.factory.ContextFactory;
import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.entities.User;
import com.fluffypets.mvc.page_objects.SignUpPageInputs;
import com.fluffypets.mvc.page_objects.ValidationMessage;
import com.fluffypets.mvc.servlets.Action;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.services.UserService;
import com.fluffypets.validators.impl.SignUpValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpCheckController implements Controller {
    private static final Logger logger = LogManager.getLogger(SignUpCheckController.class.getName());

    private UserService userService;

    public SignUpCheckController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Action action, ViewModel vm) {
        String username = action.getAttribute("username");
        String email = action.getAttribute("email");
        String password = action.getAttribute("password");
        String password2 = action.getAttribute("password2");
        String role = "user";
        SignUpValidator validator = new SignUpValidator();
        ValidationMessage<SignUpPageInputs> validation = validator.validate(new SignUpPageInputs(email, username, password, password2));

        if (!validation.getValidationMessage().equals("Ok")) {
            vm.setView("signup");
            vm.setAttribute("validationSignUp", validation);
            return vm;
        }

        User exists = userService.findByEmail(email);

        if (validation.getValidationMessage().equals("Ok") && (exists == null)) {

            User user = new User(1, username, ContextFactory.md5Custom(password, logger), email, role);
            user = userService.create(user);
            vm.setAttribute("user", user);
            vm.setView("home");
            return vm;
        } else {
            vm.setView("signup");
            validation.setValidationMessage("email is reserved");
            vm.setAttribute("validationSignUp", validation);
            return vm;
        }
    }

}
