package com.fluffypets.mvc.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Integer orderId;
    private Integer userId;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String status;
    private List<OrderItem> items;
    private String comment;

    public Order(Integer orderId, Integer userId, LocalDate orderDate, LocalDate deliveryDate, String status, List<OrderItem> items, String comment) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.items = items;
        this.comment = comment;
    }

    public Order(Integer userId, LocalDate orderDate, LocalDate deliveryDate, String status, List<OrderItem> items, String comment) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.items = items;
        this.comment = comment;
    }



    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getUserId() != order.getUserId()) return false;
        if (getOrderDate() != null ? !getOrderDate().equals(order.getOrderDate()) : order.getOrderDate() != null)
            return false;
        if (getDeliveryDate() != null ? !getDeliveryDate().equals(order.getDeliveryDate()) : order.getDeliveryDate() != null)
            return false;
        if (!getStatus().equals(order.getStatus())) return false;
        if (getItems() != null ? !getItems().equals(order.getItems()) : order.getItems() != null) return false;
        return getComment() != null ? getComment().equals(order.getComment()) : order.getComment() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getUserId() ^ (getUserId() >>> 32));
        result = 31 * result + (getOrderDate() != null ? getOrderDate().hashCode() : 0);
        result = 31 * result + (getDeliveryDate() != null ? getDeliveryDate().hashCode() : 0);
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + (getItems() != null ? getItems().hashCode() : 0);
        result = 31 * result + (getComment() != null ? getComment().hashCode() : 0);
        return result;
    }
}
