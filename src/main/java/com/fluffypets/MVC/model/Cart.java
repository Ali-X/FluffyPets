package com.fluffypets.MVC.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Cart {
    private List<ProductInCart> productsInCart = new ArrayList<>();
    private User user;
    private String comment;

    public Cart(User cartOwner){
        this.user=cartOwner;
    }

    public List<ProductInCart> getProductInCarts() {
        return this.productsInCart;
    }

    public User getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public void setProductInCarts(List<ProductInCart> cartContent) {
        this.productsInCart =cartContent;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Order buyCartContent(){
        List<OrderItem> items=new ArrayList<>();
        for (ProductInCart productInCart:productsInCart)items.add(productInCart.toOrderItem());
        return new Order(user.getId(), LocalDate.now(),null,"new order", items,comment);
    }

    public BigDecimal getTotalPrice() {
        return productsInCart.stream()
                .map(product -> product.getProduct().getPrice().multiply(new BigDecimal(product.getNumber()))).
                        reduce(new BigDecimal("0"), BigDecimal::add);
    }

    public void addGood(Product product) {
        boolean alreadyContained = productsInCart.stream()
                .map(productInCart -> productInCart.getProduct().getId())
                .anyMatch(item -> item.equals(product.getId()));
        if (!alreadyContained) productsInCart.add(new ProductInCart(product, 1));
        else {
            for (ProductInCart productInCart : productsInCart) {
                if (Objects.equals(productInCart.getProduct().getId(), product.getId())) productInCart.plus();
            }
        }
    }

    public void removeGood(Product product) {
        boolean alreadyContained = productsInCart.stream()
                .map(productInCart -> productInCart.getProduct().getId())
                .anyMatch(item -> item.equals(product.getId()));
        if (alreadyContained) {
            for (ProductInCart productInCart : productsInCart) {
                if (Objects.equals(productInCart.getProduct().getId(), product.getId())) productInCart.minus();
            }
            Iterator iterator =productsInCart.iterator();
            while (iterator.hasNext()){
                ProductInCart productInCart= (ProductInCart) iterator.next();
                if (productInCart.getNumber().equals(0))iterator.remove();}
        }
    }
}
