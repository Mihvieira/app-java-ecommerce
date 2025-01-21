package com.ecommerce.app.dto;

import org.springframework.beans.BeanUtils;

import com.ecommerce.app.entities.OrderItem;
import com.ecommerce.app.entities.pk.OrderItemPK;

public class OrderItemDTO {

    private OrderItemPK id = new OrderItemPK();
    private Integer quantity;
    private Double price;

    public OrderItemDTO() {
    }

    public OrderItemDTO(OrderItem entity){
        BeanUtils.copyProperties(entity, this);
    }

    public OrderItemPK getId() {
        return id;
    }

    public void setId(OrderItemPK id) {
        this.id = id;
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
