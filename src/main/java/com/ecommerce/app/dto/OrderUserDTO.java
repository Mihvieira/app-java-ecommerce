package com.ecommerce.app.dto;

import java.time.Instant;
import java.time.OffsetDateTime;

import com.ecommerce.app.entities.OrderStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class OrderUserDTO {

    private String userName;
    private Long orderId;
    @JsonDeserialize
    @JsonSerialize
    private Instant orderMoment;
    private Integer orderStatus;

    public OrderUserDTO() {
    }

    public OrderUserDTO(String userName, Long orderId, OffsetDateTime orderMoment, Integer orderStatus) {
        this.userName = userName;
        this.orderId = orderId;
        this.orderMoment = orderMoment.toInstant();
        this.orderStatus = orderStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Instant getOrderMoment() {
        return orderMoment;
    }

    public void setOrderMoment(Instant orderMoment) {
        this.orderMoment = orderMoment;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if(orderStatus != null){
            this.orderStatus = orderStatus.getStatus();
        }
    }

    
}
