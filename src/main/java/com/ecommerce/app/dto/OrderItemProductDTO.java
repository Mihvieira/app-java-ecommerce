package com.ecommerce.app.dto;

import java.time.Instant;
import java.time.OffsetDateTime;

public class OrderItemProductDTO {

    private Long IdOrder;
    private Instant moment;
    private Integer orderStatus;
    private String nameProduct;
    private Integer quantity;
    private Double unitPrice;
    private Double total;

    public OrderItemProductDTO() {
    }

    public OrderItemProductDTO(Long idOrder, OffsetDateTime moment, Integer orderStatus,
            Integer quantity, Double unitPrice, String nameProduct) {
        IdOrder = idOrder;
        this.moment = moment.toInstant();
        this.orderStatus = orderStatus;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(Long idOrder) {
        IdOrder = idOrder;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotal(){
        return this.quantity * this.unitPrice;
    }

}
