package com.ecommerce.app.dto;

import java.time.Instant;
import com.ecommerce.app.entities.Order;
import com.ecommerce.app.entities.OrderStatus;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus orderStatus;
    private Long clientId;

    public OrderDTO(){

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
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    } 
    

}
