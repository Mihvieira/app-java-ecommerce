package com.ecommerce.app.dto;

import java.time.Instant;

import com.ecommerce.app.entities.Payment;

public class PaymentDTO {

    private Long id;
    private Instant moment;
    private Long order_id;

    public PaymentDTO() {
    }

    public PaymentDTO(Payment entity) {
        setId(entity.getId());
        setMoment(entity.getMoment());
        setOrder_id(entity.getOrder().getId());
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

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }


}
