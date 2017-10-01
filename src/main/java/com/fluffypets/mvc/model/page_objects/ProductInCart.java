package com.fluffypets.mvc.model.page_objects;

import com.fluffypets.mvc.model.OrderItem;
import com.fluffypets.mvc.model.Product;

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


    public void plus(){if (this.getNumber()>0)this.setNumber(this.getNumber()+1);else this.setNumber(1);}

    public void minus(){if (this.getNumber()>1)this.setNumber(this.getNumber()-1);else this.setNumber(0);}

    public OrderItem toOrderItem(){
        return new OrderItem(this.product.getId(),number,this.getProduct().getPrice());
    }

}
