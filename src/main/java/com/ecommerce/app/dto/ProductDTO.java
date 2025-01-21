package com.ecommerce.app.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.ecommerce.app.entities.Category;
import com.ecommerce.app.entities.OrderItem;

public class ProductDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Set<Category> categories = new HashSet<>();
    private Set<OrderItem> items = new HashSet<>();

    
    public ProductDTO() {
    }


    public ProductDTO(Long id, String name, String description, Double price, String imgUrl, Set<Category> categories,
            Set<OrderItem> items) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.categories = categories;
        this.items = items;
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


    public Set<OrderItem> getItems() {
        return items;
    }


    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    

}
