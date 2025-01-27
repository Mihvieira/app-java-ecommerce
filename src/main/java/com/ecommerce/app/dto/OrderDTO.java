package com.ecommerce.app.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;

import com.ecommerce.app.entities.Order;
import com.ecommerce.app.entities.OrderStatus;

public class OrderDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private Instant moment;
    private Integer orderStatus;
    private Long clientId;

    public OrderDTO(){

    }

    public OrderDTO(Long id, OffsetDateTime moment, OrderStatus orderStatus, Long clientId) {
        this.id = id;
        this.moment = moment.toInstant();
        setOrderStatus(orderStatus);
        this.clientId = clientId;
    }


    public OrderDTO(Order entity){
        setId(entity.getId());
        setMoment(entity.getMoment());
        setOrderStatus(entity.getOrderStatus());
        setClientId(entity.getClient().getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if(orderStatus != null){
            this.orderStatus = orderStatus.getStatus();
        }
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    } 
    

}
