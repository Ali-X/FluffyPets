package com.fluffypets.validators.impl;

import com.fluffypets.entities.User;
import com.fluffypets.mvc.page_objects.FieldStatus;
import com.fluffypets.mvc.page_objects.SignUpPageInputs;
import com.fluffypets.mvc.page_objects.ValidationMessage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SignUpValidatorTest {
    @Test
    public void testAcceptable() {
        SignUpValidator signUpValidator = new SignUpValidator();
        SignUpPageInputs validInputs=new SignUpPageInputs("goodEmail@mail.com","VovaSuperMan","password","password");
        ValidationMessage<SignUpPageInputs> answer=signUpValidator.validate(validInputs);

        List<FieldStatus> statuses=new ArrayList<>();
        FieldStatus emailStatus = new FieldStatus(true, "email");
        FieldStatus usernameStatus = new FieldStatus(true, "username");
        FieldStatus passwordStatus = new FieldStatus(true, "password");
        FieldStatus password2Status = new FieldStatus(true, "password2");

        String message="Ok";
        statuses.add(emailStatus);
        statuses.add(usernameStatus);
        statuses.add(passwordStatus);
        statuses.add(password2Status);
        ValidationMessage<SignUpPageInputs> expectedAnswer = new ValidationMessage<>(validInputs, message, statuses);
        assertEquals(expectedAnswer,answer);
    }

    @Test
    public void testBadEmail() {
        SignUpValidator signUpValidator = new SignUpValidator();
        SignUpPageInputs invalidInputs=new SignUpPageInputs("jopa","VovaSuperMan","password","password");
        ValidationMessage<SignUpPageInputs> answer=signUpValidator.validate(invalidInputs);

        List<FieldStatus> statuses=new ArrayList<>();
        FieldStatus emailStatus = new FieldStatus(false, "email");
        FieldStatus usernameStatus = new FieldStatus(true, "username");
        FieldStatus passwordStatus = new FieldStatus(true, "password");
        FieldStatus password2Status = new FieldStatus(true, "password2");

        String message="wrong email";
        statuses.add(emailStatus);
        statuses.add(usernameStatus);
        statuses.add(passwordStatus);
        statuses.add(password2Status);
        ValidationMessage<SignUpPageInputs> expectedAnswer = new ValidationMessage<>(invalidInputs, message, statuses);
        assertEquals(expectedAnswer,answer);
    }

    @Test
    public void testBadPassword() {
        SignUpValidator signUpValidator = new SignUpValidator();
        SignUpPageInputs invalidInputs=new SignUpPageInputs("goodEmail@mail.com","VovaSuperMan","<script>alert('hi');</script>","<script>alert('hi');</script>");
        ValidationMessage<SignUpPageInputs> answer=signUpValidator.validate(invalidInputs);

        List<FieldStatus> statuses=new ArrayList<>();
        FieldStatus emailStatus = new FieldStatus(true, "email");
        FieldStatus usernameStatus = new FieldStatus(true, "username");
        FieldStatus passwordStatus = new FieldStatus(false, "password");
        FieldStatus password2Status = new FieldStatus(false, "password2");

        String message="password length should be 4<=l<=32,\nit can contain letters, numbers and '@'"+
                "\npassword length should be 4<=l<=32,\n it can contain letters, numbers and '@'";
        statuses.add(emailStatus);
        statuses.add(usernameStatus);
        statuses.add(passwordStatus);
        statuses.add(password2Status);
        ValidationMessage<SignUpPageInputs> expectedAnswer = new ValidationMessage<>(invalidInputs, message, statuses);
        assertEquals(expectedAnswer,answer);
    }

    @Test
    public void testDifferentPasswords() {
        SignUpValidator signUpValidator = new SignUpValidator();
        SignUpPageInputs invalidInputs=new SignUpPageInputs("goodEmail@mail.com","VovaSuperMan","password","<script>alert('hi');</script>");
        ValidationMessage<SignUpPageInputs> answer=signUpValidator.validate(invalidInputs);

        List<FieldStatus> statuses=new ArrayList<>();
        FieldStatus emailStatus = new FieldStatus(true, "email");
        FieldStatus usernameStatus = new FieldStatus(true, "username");
        FieldStatus passwordStatus = new FieldStatus(false, "password");
        FieldStatus password2Status = new FieldStatus(false, "password2");

        String message="Different passwords";
        statuses.add(emailStatus);
        statuses.add(usernameStatus);
        statuses.add(passwordStatus);
        statuses.add(password2Status);
        ValidationMessage<SignUpPageInputs> expectedAnswer = new ValidationMessage<>(invalidInputs, message, statuses);
        assertEquals(expectedAnswer,answer);
    }
}