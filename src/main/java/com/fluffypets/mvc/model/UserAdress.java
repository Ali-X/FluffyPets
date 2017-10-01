package com.fluffypets.mvc.model;

public class UserAdress {
    private Integer userAdressId;
    private Integer userId;
    private String fullName;
    private String district;
    private String area;
    private String street;
    private String app;
    private String phone;


    public UserAdress(Integer userAdressId, Integer userId, String fullName, String district, String area, String street, String app, String phone) {
        this.userAdressId = userAdressId;
        this.userId = userId;
        this.fullName = fullName;
        this.district = district;
        this.area = area;
        this.street = street;
        this.app = app;
        this.phone = phone;
    }

    public UserAdress(Integer userId, String fullName, String district, String area, String street, String app, String phone) {
        this.userId = userId;
        this.fullName = fullName;
        this.district = district;
        this.area = area;
        this.street = street;
        this.app = app;
        this.phone = phone;
    }

    public Integer getUserDataId() {
        return userAdressId;
    }

    public void setUserDataId(Integer userDataId) {
        this.userAdressId = userDataId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
