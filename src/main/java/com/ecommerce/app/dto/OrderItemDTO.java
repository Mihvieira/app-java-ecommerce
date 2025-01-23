package com.ecommerce.app.dto;

import com.ecommerce.app.entities.OrderItem;

public class OrderItemDTO {

    private Long order_id;
    private Long product_id;
    private Integer quantity;
    private Double price;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long order_id, Long product_id, Integer quantity, Double price) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItemDTO(OrderItem entity){
        if (entity.getId().getOrder() != null && entity.getId().getProduct() != null) {
            setOrder_id(entity.getId().getOrder().getId());
        }
        if (entity.getId().getProduct() != null) {
            setProduct_id(entity.getId().getProduct().getId());
        }
        setQuantity(entity.getQuantity());
        setPrice(entity.getPrice());
    }


    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
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
