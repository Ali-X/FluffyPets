package com.fluffypets.MVC.model;

public class Product {

    private Integer id;
    private String name;
    private String decription;
    private Category category;

    public Product(Integer id, String name, String decription, Category category) {
        this.id = id;
        this.name = name;
        this.decription = decription;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
