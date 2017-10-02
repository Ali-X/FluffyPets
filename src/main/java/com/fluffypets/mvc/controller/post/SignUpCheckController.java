package com.fluffypets.mvc.controller.post;

import com.fluffypets.mvc.controller.Controller;
import com.fluffypets.mvc.model.User;
import com.fluffypets.mvc.model.page_objects.SignUpPageInputs;
import com.fluffypets.mvc.model.page_objects.ValidationMessage;
import com.fluffypets.mvc.servlets.Command;
import com.fluffypets.mvc.servlets.ViewModel;
import com.fluffypets.factory.ContextFactory;
import com.fluffypets.servicies.user.UserService;
import com.fluffypets.servicies.validators.SignUpValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpCheckController implements Controller {
    private static final Logger logger = LogManager.getLogger(SignUpCheckController.class.getName());

    private UserService userService;

    public SignUpCheckController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Command command, ViewModel vm) {
        String username = command.getAttribute("username");
        String email = command.getAttribute("email");
        String password = command.getAttribute("password");
        String password2 = command.getAttribute("password2");
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

            User user = new User(1, username, password, email, role);
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
