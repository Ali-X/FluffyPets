package com.fluffypets.entities.enumes;

public enum ProductStatus {
    NEW("new"), OUT("running out"), COMING("coming soon"), MANY("many");

    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
