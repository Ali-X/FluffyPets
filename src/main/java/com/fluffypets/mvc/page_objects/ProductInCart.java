package com.fluffypets.mvc.page_objects;

import com.fluffypets.entities.OrderItem;
import com.fluffypets.entities.Product;

public class ProductInCart {
    private Product product;
    private Integer number;

    ProductInCart(Product product, Integer number) {
        this.product = product;
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getNumber() {
        return number;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


    void plus(Integer n) {
        if (this.number.equals(n)){
        if (this.getNumber() > 0) this.setNumber(this.getNumber() + 1);
        else this.setNumber(1);}
        else {this.number=n;}
    }

    void minus(Integer n) {
        if (this.number.equals(n)){
            if (this.getNumber() > 1) this.setNumber(this.getNumber() - 1);
        else this.setNumber(0);}
        else {this.number=n;}
    }

    public OrderItem toOrderItem() {
        return new OrderItem(this.product.getId(), number, this.getProduct().getPrice());
    }

}
