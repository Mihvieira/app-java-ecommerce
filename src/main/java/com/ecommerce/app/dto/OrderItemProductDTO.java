package com.ecommerce.app.dto;

import java.time.Instant;

public class OrderItemProductDTO {

    private Integer IdOrder;
    private Instant moment;
    private Integer orderStatus;
    private String nameProduct;
    private Integer quantity;
    private Double unitPrice;
    private Double total;
    
    public OrderItemProductDTO() {
    }

    public OrderItemProductDTO(Integer idOrder, Instant moment, Integer orderStatus, String nameProduct, Integer quantity, Double unitPrice, Double total) {
        IdOrder = idOrder;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public Integer getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(Integer idOrder) {
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    
}
