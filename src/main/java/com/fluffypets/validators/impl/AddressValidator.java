package com.fluffypets.validators.impl;

import com.fluffypets.entities.UserAddress;
import com.fluffypets.mvc.page_objects.FieldStatus;
import com.fluffypets.mvc.page_objects.ValidationMessage;
import com.fluffypets.validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressValidator implements Validator<UserAddress> {
    @Override
    public synchronized ValidationMessage<UserAddress> validate(UserAddress userAddress) {

        List<FieldStatus> statuses = new ArrayList<>();
        ValidationMessage<UserAddress> response;
        FieldStatus fullNameStatus;
        FieldStatus districtStatus;
        FieldStatus areaStatus;
        FieldStatus streetStatus;
        FieldStatus appStatus;
        FieldStatus telephoneStatus;
        String fullName = userAddress.getFullName();
        String district = userAddress.getDistrict();
        String area = userAddress.getArea();
        String street = userAddress.getStreet();
        String app = userAddress.getApp();
        String phone = userAddress.getPhone();
        String message;
        String usernameRegexp = "^[a-zA-Zа-яА-Я0-9@.\\sЇїІіґҐ_]{2,30}$";
        String telephoneRegex = "^(\\+?\\d{1,3}\\s)?\\(?\\d{2,3}\\)?[\\s.-]\\d{2,3}[\\s.-]\\d{2,4}$";
        Pattern textP = Pattern.compile(usernameRegexp);
        Matcher fullNameM = textP.matcher(fullName);
        Matcher districtM = textP.matcher(district);
        Matcher areaM = textP.matcher(area);
        Matcher streetM = textP.matcher(street);
        Matcher appM = textP.matcher(app);
        Pattern telephoneP = Pattern.compile(telephoneRegex);
        Matcher telephoneM = telephoneP.matcher(phone);

        if (fullNameM.matches()) {
            fullNameStatus = new FieldStatus(true, "Fullname");
            message = "";
        } else {
            fullNameStatus = new FieldStatus(false, "Fullname");
            message = "name can contain letters, numbers and '@' '.' ' '";
        }

        if (districtM.matches()) {
            districtStatus = new FieldStatus(true, "District");
        } else {
            districtStatus = new FieldStatus(false, "District");
            if (message.equals("")) {
                message = "district can contain letters, numbers and '@' '.' ' '";
            } else {
                message += "\ndistrict can contain letters, numbers and '@' '.' ' '";
            }
        }

        if (areaM.matches()) {
            areaStatus = new FieldStatus(true, "Area");
        } else {
            areaStatus = new FieldStatus(false, "Area");
            if (message.equals("")) {
                message = "area can contain letters, numbers and '@' '.' ' '";
            } else {
                message += "\narea can contain letters, numbers and '@' '.' ' '";
            }
        }

        if (streetM.matches()) {
            streetStatus = new FieldStatus(true, "Street");
        } else {
            streetStatus = new FieldStatus(false, "Street");
            if (message.equals("")) {
                message = "street can contain letters, numbers and '@' '.' ' '";
            } else {
                message += "\nstreet can contain letters, numbers and '@' '.' ' '";
            }
        }

        if (appM.matches()) {
            appStatus = new FieldStatus(true, "App");
        } else {
            appStatus = new FieldStatus(false, "App");
            if (message.equals("")) {
                message = "apartment can contain letters, numbers and '@' '.' ' '";
            } else {
                message += "\napartment can contain letters, numbers and '@' '.' ' '";
            }
        }

        if (telephoneM.matches()) {
            telephoneStatus = new FieldStatus(true, "Phone number");
        } else {
            telephoneStatus = new FieldStatus(false, "Phone number");
            if (message.equals("")) {
                message = "telephone formats: 123-456-7890; (123) 456-7890; 123 456 78 90;  123.456.7890;  +91 (123) 456-7890";
            } else {
                message += "\ntelephone formats: 123-456-7890; (123) 456-7890; 123 456 78 90;  123.456.7890;  +91 (123) 456-7890;";
            }
        }

        if (message.equals("")) {
            message = "Ok";
        }

        statuses.add(fullNameStatus);
        statuses.add(districtStatus);
        statuses.add(areaStatus);
        statuses.add(streetStatus);
        statuses.add(appStatus);
        statuses.add(telephoneStatus);
        response = new ValidationMessage<>(userAddress, message, statuses);
        return response;
    }

}
