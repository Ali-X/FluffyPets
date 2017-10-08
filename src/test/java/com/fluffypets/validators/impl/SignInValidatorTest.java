package com.fluffypets.validators.impl;

import com.fluffypets.entities.User;
import com.fluffypets.mvc.page_objects.FieldStatus;
import com.fluffypets.mvc.page_objects.ValidationMessage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SignInValidatorTest {
    @Test
    public void testAcceptableUserPassword() {
        SignInValidator signInValidator = new SignInValidator();
        User validLoginPassword=new User("Vova_SuperMan","123123");
        ValidationMessage<User> answer=signInValidator.validate(validLoginPassword);

        List<FieldStatus> statuses=new ArrayList<>();
        FieldStatus usernameStatus = new FieldStatus(true, "username");
        FieldStatus passwordStatus = new FieldStatus(true, "password");

        String message="Ok";
        statuses.add(usernameStatus);
        statuses.add(passwordStatus);
        ValidationMessage<User> expectedAnswer = new ValidationMessage<>(validLoginPassword, message, statuses);
        assertEquals(expectedAnswer,answer);
    }

    @Test
    public void testBadPassword() {
        SignInValidator signInValidator = new SignInValidator();
        User invalidLoginPassword=new User("Vova_SuperMan","<script>alert('hi');</script>");
        ValidationMessage<User> answer=signInValidator.validate(invalidLoginPassword);

        List<FieldStatus> statuses=new ArrayList<>();
        FieldStatus usernameStatus = new FieldStatus(true, "username");
        FieldStatus passwordStatus = new FieldStatus(false, "password");
        String message="password length should be 4<=l<=32,\nit can contain letters, numbers and '@'";
        statuses.add(usernameStatus);
        statuses.add(passwordStatus);
        ValidationMessage<User> expectedAnswer = new ValidationMessage<>(invalidLoginPassword, message, statuses);
        assertEquals(expectedAnswer,answer);
    }

    @Test
    public void testBadUsername() {
        SignInValidator signInValidator = new SignInValidator();
        User invalidLoginPassword=new User("Ivan,password); Drop table Pets.users;","123123");
        ValidationMessage<User> answer=signInValidator.validate(invalidLoginPassword);

        List<FieldStatus> statuses=new ArrayList<>();
        FieldStatus usernameStatus = new FieldStatus(false, "username");
        FieldStatus passwordStatus = new FieldStatus(true, "password");
        String message="username length should be 5<=l<=32,\n it can contain letters, numbers and '@'";
        statuses.add(usernameStatus);
        statuses.add(passwordStatus);
        ValidationMessage<User> expectedAnswer = new ValidationMessage<>(invalidLoginPassword, message, statuses);
        assertEquals(expectedAnswer,answer);
    }
}