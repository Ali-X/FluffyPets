package com.fluffypets.mvc.model.enumes;

public enum UserRole {
    ADMIN("admin"), USER("user"), BLOCKED("blocked"), COURIER("courier");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
