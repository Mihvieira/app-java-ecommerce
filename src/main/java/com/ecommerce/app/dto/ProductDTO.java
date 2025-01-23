package com.ecommerce.app.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.ecommerce.app.entities.Category;
import com.ecommerce.app.entities.Product;

public class ProductDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Set<Category> categories = new HashSet<>();

    
    public ProductDTO() {
    }

    public ProductDTO(Product entity){
        setId(entity.getId());
        setName(entity.getName());
        setPrice(entity.getPrice());
        setDescription(entity.getDescription());
        setCategories(entity.getCategories());
        setImgUrl(entity.getImgUrl());
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Double getPrice() {
        return price;
    }


    public void setPrice(Double price) {
        this.price = price;
    }


    public String getImgUrl() {
        return imgUrl;
    }


    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public Set<Category> getCategories() {
        return categories;
    }


    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    

}
