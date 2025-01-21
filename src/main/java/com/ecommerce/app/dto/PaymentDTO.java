package com.ecommerce.app.dto;

import java.time.Instant;

import com.ecommerce.app.entities.Order;

public class PaymentDTO {

    private Long id;
    private Instant moment;
    private Order order;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    
    

}
