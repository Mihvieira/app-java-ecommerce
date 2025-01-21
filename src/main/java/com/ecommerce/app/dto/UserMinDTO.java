package com.ecommerce.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.app.entities.Order;

public class UserMinDTO {

    private String name;
    private List<Order> orders = new ArrayList<>();

    
    public UserMinDTO() {
    }


    public UserMinDTO(String name, List<Order> orders) {
        this.name = name;
        this.orders = orders;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<Order> getOrders() {
        return orders;
    }


    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


}
