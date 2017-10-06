package com.fluffypets.validators.impl;

import com.fluffypets.entities.User;
import com.fluffypets.mvc.page_objects.FieldStatus;
import com.fluffypets.mvc.page_objects.ValidationMessage;
import com.fluffypets.validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInValidator implements Validator<User> {
    @Override
    public synchronized ValidationMessage<User> validate(User user) {

        List<FieldStatus> statuses = new ArrayList<>();
        ValidationMessage<User> response;
        FieldStatus usernameStatus;
        FieldStatus passwordStatus;
        String username = user.getUserName();
        String password = user.getPassword();
        String message;
        String usernameRegexp = "^[a-zA-Zа-яА-Я0-9@.]{5,32}$";
        String passwordRegexp = "^[a-zA-Zа-яА-Я0-9@.]{4,32}$";
        Pattern usernameP = Pattern.compile(usernameRegexp);
        Matcher usernameM = usernameP.matcher(username);
        Pattern passwordP = Pattern.compile(passwordRegexp);
        Matcher passwordM = passwordP.matcher(password);

        if (usernameM.matches()) {
            usernameStatus = new FieldStatus(true, "username");
            message = "";
        } else {
            usernameStatus = new FieldStatus(false, "username");
            message = "username length should be 5<=l<=32,\n it can contain letters, numbers and '@'";
        }
        if (passwordM.matches()) {
            passwordStatus = new FieldStatus(true, "password");
            if (message.equals("")) {message="Ok";}
        } else {
            passwordStatus = new FieldStatus(false, "password");
            if (message.equals("")) {
                message = "password length should be 4<=l<=32,\nit can contain letters, numbers and '@'";
            } else {
                message += "\npassword length should be 4<=l<=32,\n it can contain letters, numbers and '@'";
            }
        }
        statuses.add(usernameStatus);
        statuses.add(passwordStatus);
        response = new ValidationMessage<>(user, message, statuses);
        return response;
    }
}
