package com.ecommerce.app.dto;

import com.ecommerce.app.entities.OrderItem;

public class OrderItemDTO {

    private Long id_order;
    private Long id_product;
    private Integer quantity;
    private Double price;

    public OrderItemDTO() {
    }

    public OrderItemDTO(OrderItem entity){
        setId_order(entity.getOrder().getId());
        setId_product(entity.getProduct().getId());
        setQuantity(entity.getQuantity());
        setPrice(entity.getPrice());
    }

    
    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    
}
