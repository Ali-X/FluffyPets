package com.fluffypets.validators.impl;

import com.fluffypets.mvc.page_objects.FieldStatus;
import com.fluffypets.mvc.page_objects.SignUpPageInputs;
import com.fluffypets.mvc.page_objects.ValidationMessage;
import com.fluffypets.validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpValidator implements Validator<SignUpPageInputs> {
    @Override
    public synchronized ValidationMessage<SignUpPageInputs> validate(SignUpPageInputs signUpPageInputs) {

        List<FieldStatus> statuses = new ArrayList<>();
        ValidationMessage<SignUpPageInputs> response;
        FieldStatus usernameStatus;
        FieldStatus passwordStatus;
        FieldStatus emailStatus;
        FieldStatus password2Status;
        String email = signUpPageInputs.getEmail();
        String username = signUpPageInputs.getUsername();
        String password = signUpPageInputs.getPassword();
        String password2 = signUpPageInputs.getPassword2();
        String message;
        String emailRegexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String usernameRegexp = "^[a-zA-Zа-яА-Я0-9@.]{5,32}$";
        String passwordRegexp = "^[a-zA-Zа-яА-Я0-9@.]{4,32}$";
        Pattern emailP = Pattern.compile(emailRegexp);
        Matcher emailM = emailP.matcher(email);
        Pattern usernameP = Pattern.compile(usernameRegexp);
        Matcher usernameM = usernameP.matcher(username);
        Pattern passwordP = Pattern.compile(passwordRegexp);
        Matcher passwordM = passwordP.matcher(password);
        Matcher password2M = passwordP.matcher(password2);

        if (emailM.matches()) {
            emailStatus = new FieldStatus(true, "email");
            message = "";
        } else {
            emailStatus = new FieldStatus(false, "email");
            message = "wrong email";
        }

        if (usernameM.matches()) {
            usernameStatus = new FieldStatus(true, "username");
        } else {
            usernameStatus = new FieldStatus(false, "username");
            if (message.equals("")) {
                message = "username length should be 5<=l<=32,\n it can contain letters, numbers and '@'";
            } else {
                message += "\nusername length should be 5<=l<=32,\n it can contain letters, numbers and '@'";
            }
        }

        if (passwordM.matches()) {
            passwordStatus = new FieldStatus(true, "password");
        } else {
            passwordStatus = new FieldStatus(false, "password");
            if (message.equals("")) {
                message = "password length should be 4<=l<=32,\nit can contain letters, numbers and '@'";
            } else {
                message += "\npassword length should be 4<=l<=32,\n it can contain letters, numbers and '@'";
            }
        }

        if (password2M.matches()) {
            password2Status = new FieldStatus(true, "password2");
        } else {
            password2Status = new FieldStatus(false, "password2");
            if (message.equals("")) {
                message = "password length should be 4<=l<=32,\nit can contain letters, numbers and '@'";
            } else {
                message += "\npassword length should be 4<=l<=32,\n it can contain letters, numbers and '@'";
            }
        }

        if (!password.equals(password2)) {
            message = "Different passwords";
            passwordStatus.setStatus(false);
            password2Status.setStatus(false);
        }

        if (message.equals("")) {
            message = "Ok";
        }

        statuses.add(emailStatus);
        statuses.add(usernameStatus);
        statuses.add(passwordStatus);
        statuses.add(password2Status);
        response = new ValidationMessage<>(signUpPageInputs, message, statuses);
        return response;
    }
}
