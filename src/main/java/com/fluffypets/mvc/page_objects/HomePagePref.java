package com.fluffypets.mvc.page_objects;

import com.fluffypets.entities.Category;
import com.fluffypets.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class HomePagePref {
    private List<Category> categoryList = new ArrayList<>();
    private String price;
    private List<Product> products = new ArrayList<>();
    private String order;
    private Integer paginationMax;
    private Integer paginationStep;
    private Integer pagination;

    public HomePagePref(List<Category> categoryList, String price, List<Product> products, String order, Integer paginationMax, Integer paginationStep, Integer pagination) {
        this.categoryList = categoryList;
        this.price = price;
        this.products = products;
        this.order = order;
        this.paginationMax = paginationMax;
        this.paginationStep = paginationStep;
        this.pagination = pagination;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getPaginationMax() {
        return paginationMax;
    }

    public void setPaginationMax(Integer paginationMax) {
        this.paginationMax = paginationMax;
    }

    public Integer getPaginationStep() {
        return paginationStep;
    }

    public void setPaginationStep(Integer paginationStep) {
        this.paginationStep = paginationStep;
    }

    public Integer getPagination() {
        return pagination;
    }

    public void setPagination(Integer pagination) {
        this.pagination = pagination;
    }
}
