package com.fluffypets.mvc.model.page_objects;

public class FieldStatus {
    private Boolean status;
    private String name;

    public FieldStatus(Boolean status, String name) {
        this.status = status;
        this.name = name;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
