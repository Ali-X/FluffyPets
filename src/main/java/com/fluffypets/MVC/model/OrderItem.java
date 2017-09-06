package com.fluffypets.MVC.model;

import java.math.BigDecimal;

public class OrderItem {
    private  long itemId;
    private long productId;
    private long orderId;
    private int     quantity;
    private BigDecimal currentPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;

        OrderItem orderItem = (OrderItem) o;

        if (getProductId() != orderItem.getProductId()) return false;
        return getOrderId() == orderItem.getOrderId();
    }

    @Override
    public int hashCode() {
        int result = (int) (getProductId() ^ (getProductId() >>> 32));
        result = 31 * result + (int) (getOrderId() ^ (getOrderId() >>> 32));
        return result;
    }

    public OrderItem(long itemId, long productId, long orderId, int quantity, BigDecimal currentPrice) {
        this.itemId = itemId;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
    }

    public OrderItem(long productId, long orderId, int quantity, BigDecimal currentPrice) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
