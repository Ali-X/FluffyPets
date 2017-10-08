package com.fluffypets.validators.impl;

import com.fluffypets.entities.User;
import com.fluffypets.entities.UserAddress;
import com.fluffypets.mvc.page_objects.FieldStatus;
import com.fluffypets.mvc.page_objects.ValidationMessage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AddressValidatorTest {
    @Test
    public void testValid() {
        AddressValidator addressValidator = new AddressValidator();
        UserAddress validUserAddress = new UserAddress(0, "Vova Putin", "Russia", "Moscow", "Lubjanka", "app 1", "+38 063 45 67");
        ValidationMessage<UserAddress> answer = addressValidator.validate(validUserAddress);

        List<FieldStatus> statuses = new ArrayList<>();
        FieldStatus fullNameStatus = new FieldStatus(true, "Fullname");
        FieldStatus districtStatus = new FieldStatus(true, "District");
        FieldStatus areaStatus = new FieldStatus(true, "Area");
        FieldStatus streetStatus = new FieldStatus(true, "Street");
        FieldStatus appStatus = new FieldStatus(true, "App");
        FieldStatus telephoneStatus = new FieldStatus(true, "Phone number");

        String message = "Ok";
        statuses.add(fullNameStatus);
        statuses.add(districtStatus);
        statuses.add(areaStatus);
        statuses.add(streetStatus);
        statuses.add(appStatus);
        statuses.add(telephoneStatus);
        ValidationMessage<UserAddress> expectedAnswer = new ValidationMessage<>(validUserAddress, message, statuses);
        assertEquals(expectedAnswer, answer);
    }

    @Test
    public void testInvalidFullname() {
        AddressValidator addressValidator = new AddressValidator();
        UserAddress invalidUserAddress = new UserAddress(0, "#VovaPutin", "Russia", "Moscow", "Lubjanka", "app 1", "+38 063 45 67");
        ValidationMessage<UserAddress> answer = addressValidator.validate(invalidUserAddress);

        List<FieldStatus> statuses = new ArrayList<>();
        FieldStatus fullNameStatus = new FieldStatus(false, "Fullname");
        FieldStatus districtStatus = new FieldStatus(true, "District");
        FieldStatus areaStatus = new FieldStatus(true, "Area");
        FieldStatus streetStatus = new FieldStatus(true, "Street");
        FieldStatus appStatus = new FieldStatus(true, "App");
        FieldStatus telephoneStatus = new FieldStatus(true, "Phone number");

        String message = "name can contain letters, numbers and '@' '.' ' '";
        statuses.add(fullNameStatus);
        statuses.add(districtStatus);
        statuses.add(areaStatus);
        statuses.add(streetStatus);
        statuses.add(appStatus);
        statuses.add(telephoneStatus);
        ValidationMessage<UserAddress> expectedAnswer = new ValidationMessage<>(invalidUserAddress, message, statuses);
        assertEquals(expectedAnswer, answer);
    }

    @Test
    public void testInvalidDistrict() {
        AddressValidator addressValidator = new AddressValidator();
        UserAddress invalidUserAddress = new UserAddress(0, "Vova Putin", "Russia?", "Moscow", "Lubjanka", "app 1", "+38 063 45 67");
        ValidationMessage<UserAddress> answer = addressValidator.validate(invalidUserAddress);

        List<FieldStatus> statuses = new ArrayList<>();
        FieldStatus fullNameStatus = new FieldStatus(true, "Fullname");
        FieldStatus districtStatus = new FieldStatus(false, "District");
        FieldStatus areaStatus = new FieldStatus(true, "Area");
        FieldStatus streetStatus = new FieldStatus(true, "Street");
        FieldStatus appStatus = new FieldStatus(true, "App");
        FieldStatus telephoneStatus = new FieldStatus(true, "Phone number");

        String message = "district can contain letters, numbers and '@' '.' ' '";
        statuses.add(fullNameStatus);
        statuses.add(districtStatus);
        statuses.add(areaStatus);
        statuses.add(streetStatus);
        statuses.add(appStatus);
        statuses.add(telephoneStatus);
        ValidationMessage<UserAddress> expectedAnswer = new ValidationMessage<>(invalidUserAddress, message, statuses);
        assertEquals(expectedAnswer, answer);
    }

    @Test
    public void testInvalidArea() {
        AddressValidator addressValidator = new AddressValidator();
        UserAddress invalidUserAddress = new UserAddress(0, "Vova Putin", "Russia", "Mos!cow", "Lubjanka", "app 1", "+38 063 45 67");
        ValidationMessage<UserAddress> answer = addressValidator.validate(invalidUserAddress);

        List<FieldStatus> statuses = new ArrayList<>();
        FieldStatus fullNameStatus = new FieldStatus(true, "Fullname");
        FieldStatus districtStatus = new FieldStatus(true, "District");
        FieldStatus areaStatus = new FieldStatus(false, "Area");
        FieldStatus streetStatus = new FieldStatus(true, "Street");
        FieldStatus appStatus = new FieldStatus(true, "App");
        FieldStatus telephoneStatus = new FieldStatus(true, "Phone number");

        String message = "area can contain letters, numbers and '@' '.' ' '";
        statuses.add(fullNameStatus);
        statuses.add(districtStatus);
        statuses.add(areaStatus);
        statuses.add(streetStatus);
        statuses.add(appStatus);
        statuses.add(telephoneStatus);
        ValidationMessage<UserAddress> expectedAnswer = new ValidationMessage<>(invalidUserAddress, message, statuses);
        assertEquals(expectedAnswer, answer);
    }

    @Test
    public void testInvalidStreet() {
        AddressValidator addressValidator = new AddressValidator();
        UserAddress invalidUserAddress = new UserAddress(0, "Vova Putin", "Russia", "Moscow", "Lubjanka+", "app 1", "+38 063 45 67");
        ValidationMessage<UserAddress> answer = addressValidator.validate(invalidUserAddress);

        List<FieldStatus> statuses = new ArrayList<>();
        FieldStatus fullNameStatus = new FieldStatus(true, "Fullname");
        FieldStatus districtStatus = new FieldStatus(true, "District");
        FieldStatus areaStatus = new FieldStatus(true, "Area");
        FieldStatus streetStatus = new FieldStatus(false, "Street");
        FieldStatus appStatus = new FieldStatus(true, "App");
        FieldStatus telephoneStatus = new FieldStatus(true, "Phone number");

        String message = "street can contain letters, numbers and '@' '.' ' '";
        statuses.add(fullNameStatus);
        statuses.add(districtStatus);
        statuses.add(areaStatus);
        statuses.add(streetStatus);
        statuses.add(appStatus);
        statuses.add(telephoneStatus);
        ValidationMessage<UserAddress> expectedAnswer = new ValidationMessage<>(invalidUserAddress, message, statuses);
        assertEquals(expectedAnswer, answer);
    }

    @Test
    public void testInvalidApp() {
        AddressValidator addressValidator = new AddressValidator();
        UserAddress invalidUserAddress = new UserAddress(0, "Vova Putin", "Russia", "Moscow", "Lubjanka", "<script>alert('hi!');</script>", "+38 063 45 67");
        ValidationMessage<UserAddress> answer = addressValidator.validate(invalidUserAddress);

        List<FieldStatus> statuses = new ArrayList<>();
        FieldStatus fullNameStatus = new FieldStatus(true, "Fullname");
        FieldStatus districtStatus = new FieldStatus(true, "District");
        FieldStatus areaStatus = new FieldStatus(true, "Area");
        FieldStatus streetStatus = new FieldStatus(true, "Street");
        FieldStatus appStatus = new FieldStatus(false, "App");
        FieldStatus telephoneStatus = new FieldStatus(true, "Phone number");

        String message = "apartment can contain letters, numbers and '@' '.' ' '";
        statuses.add(fullNameStatus);
        statuses.add(districtStatus);
        statuses.add(areaStatus);
        statuses.add(streetStatus);
        statuses.add(appStatus);
        statuses.add(telephoneStatus);
        ValidationMessage<UserAddress> expectedAnswer = new ValidationMessage<>(invalidUserAddress, message, statuses);
        assertEquals(expectedAnswer, answer);
    }

    @Test
    public void testInvalidTelephone() {
        AddressValidator addressValidator = new AddressValidator();
        UserAddress invalidUserAddress = new UserAddress(0, "Vova Putin", "Russia", "Moscow", "Lubjanka", "app 1", "a4537");
        ValidationMessage<UserAddress> answer = addressValidator.validate(invalidUserAddress);

        List<FieldStatus> statuses = new ArrayList<>();
        FieldStatus fullNameStatus = new FieldStatus(true, "Fullname");
        FieldStatus districtStatus = new FieldStatus(true, "District");
        FieldStatus areaStatus = new FieldStatus(true, "Area");
        FieldStatus streetStatus = new FieldStatus(true, "Street");
        FieldStatus appStatus = new FieldStatus(true, "App");
        FieldStatus telephoneStatus = new FieldStatus(false, "Phone number");

        String message = "telephone formats: 123-456-7890; (123) 456-7890; 123 456 78 90;  123.456.7890;  +91 (123) 456-7890";
        statuses.add(fullNameStatus);
        statuses.add(districtStatus);
        statuses.add(areaStatus);
        statuses.add(streetStatus);
        statuses.add(appStatus);
        statuses.add(telephoneStatus);
        ValidationMessage<UserAddress> expectedAnswer = new ValidationMessage<>(invalidUserAddress, message, statuses);
        assertEquals(expectedAnswer, answer);
    }
}