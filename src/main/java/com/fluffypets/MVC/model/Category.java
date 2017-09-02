package com.fluffypets.MVC.model;

public class Category {
    private Integer id;
    private String name;
    private String categoryDescription;

    public Category(Integer id, String name, String categoryDescription) {
        this.id = id;
        this.name = name;
        this.categoryDescription = categoryDescription;
    }

    public Category(String name, String categoryDescription) {
        this.name = name;
        this.categoryDescription = categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryDescription() {
        return categoryDescription;
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
        return getCategoryDescription() != null ? getCategoryDescription().equals(category.getCategoryDescription()) : category.getCategoryDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + (getCategoryDescription() != null ? getCategoryDescription().hashCode() : 0);
        return result;
    }
}
