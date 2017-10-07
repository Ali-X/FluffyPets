package com.fluffypets.entities;

public class GoodRecord {
    private Integer id;
    private Integer productId;
    private Integer availableHere;
    private Integer reservedHere;
    private String status;

    public GoodRecord(Integer id, Integer productId, Integer availableHere, Integer reservedHere, String status) {
        this.id = id;
        this.productId = productId;
        this.availableHere = availableHere;
        this.reservedHere = reservedHere;
        this.status = status;
    }

    public GoodRecord(Integer productId, Integer availableHere, Integer reservedHere, String status) {
        this.productId = productId;
        this.availableHere = availableHere;
        this.reservedHere = reservedHere;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAvailableHere() {
        return availableHere;
    }

    public void setAvailableHere(Integer availableHere) {
        this.availableHere = availableHere;
    }

    public Integer getReservedHere() {
        return reservedHere;
    }

    public void setReservedHere(Integer reservedHere) {
        this.reservedHere = reservedHere;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
