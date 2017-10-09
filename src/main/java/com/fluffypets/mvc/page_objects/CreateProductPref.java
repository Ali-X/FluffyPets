package com.fluffypets.mvc.page_objects;

public class CreateProductPref {
    private String productName;
    private String producer;
    private String description;
    private String pictureURL;
    private String price;
    private String categorySelId;

    public CreateProductPref(String productName, String producer, String description, String pictureURL, String price, String categorySelId) {
        this.productName = productName;
        this.producer = producer;
        this.description = description;
        this.pictureURL = pictureURL;
        this.price = price;
        this.categorySelId = categorySelId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategorySelId() {
        return categorySelId;
    }

    public void setCategorySelId(String categorySelId) {
        this.categorySelId = categorySelId;
    }
}
