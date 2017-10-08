package com.fluffypets.mvc.page_objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldStatus)) return false;

        FieldStatus that = (FieldStatus) o;

        if (!status.equals(that.status)) return false;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
