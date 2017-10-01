package com.fluffypets.mvc.model;

public class Category {
    private Integer id;
    private String name;
    private String nameUa;

    public Category(Integer id, String name, String nameUa) {
        this.id = id;
        this.name = name;
        this.nameUa = nameUa;
    }

    public Category(String name, String nameUa) {
        this.name = name;
        this.nameUa = nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public String getNameUa() {
        return nameUa;
    }

    public Integer getId() {
        return id;
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
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (!getName().equals(category.getName())) return false;
        return getNameUa() != null ? getNameUa().equals(category.getNameUa()) : category.getNameUa() == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + (getNameUa() != null ? getNameUa().hashCode() : 0);
        return result;
    }
}
