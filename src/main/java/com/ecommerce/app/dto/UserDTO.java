package com.ecommerce.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.app.entities.Order;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private List<Order> orders = new ArrayList<>();

    public UserDTO() {
    }

} 