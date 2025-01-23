package com.ecommerce.app.dto;

import com.ecommerce.app.entities.User;

public class UserMinDTO {

    private String name;
    private String email;
    private String phone;

    
    public UserMinDTO() {
    }


    public UserMinDTO(User entity){
        setName(entity.getName());
        setEmail(entity.getEmail());
        setPhone(entity.getPhone());
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    
}
