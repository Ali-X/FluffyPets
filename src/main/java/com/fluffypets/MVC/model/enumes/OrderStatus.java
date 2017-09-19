package com.fluffypets.MVC.model.enumes;

public enum OrderStatus {
    NEW("new"), VERIFIED("verified"), DELIVERED("delivered"), CANCELED("canceled"), OVERDUE("overdue"), PROBLEMATIC("problematic");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
