package com.ecommerce.app.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.ecommerce.app.entities.OrderItem;
import com.ecommerce.app.entities.Payment;
import com.ecommerce.app.entities.User;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private Integer orderStatus;
    private User client;
    private Payment payment;
    private Set<OrderItem> items = new HashSet<>();

    public OrderDTO(){

    }

    public OrderDTO(Long id, Instant moment, Integer orderStatus, User client, Payment payment, Set<OrderItem> items) {
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.client = client;
        this.payment = payment;
        this.items = items;
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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    

}
