package com.fluffypets.mvc.model;

import java.math.BigDecimal;

public class OrderItem {
    private  Integer itemId;
    private Integer productId;
    private Integer orderId;
    private Integer     quantity;
    private BigDecimal currentPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;

        OrderItem orderItem = (OrderItem) o;

        return getProductId() == orderItem.getProductId() && getOrderId() == orderItem.getOrderId();
    }

    @Override
    public int hashCode() {
        int result = (int) (getProductId() ^ (getProductId() >>> 32));
        result = 31 * result + (int) (getOrderId() ^ (getOrderId() >>> 32));
        return result;
    }

    public OrderItem(Integer itemId, Integer productId, Integer orderId, Integer quantity, BigDecimal currentPrice) {
        this.itemId = itemId;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
    }

    public OrderItem(Integer productId, Integer orderId, Integer quantity, BigDecimal currentPrice) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
    }

    public OrderItem(Integer productId, Integer quantity, BigDecimal currentPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
