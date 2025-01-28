package com.ecommerce.app.dto;

import java.io.Serializable;
import java.util.List;

import com.ecommerce.app.entities.Product;

public class ProductCategoryDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private Double price;
    private List<String> category;
    private String imgUrl;

    
    public ProductCategoryDTO() {
    }


    public ProductCategoryDTO(Long id, String name, String description, Double price, List<String> category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
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


    public List<String> getCategory() {
        return category;
    }


    public void setCategory(List<String> category) {
        this.category = category;
    }

    
    public String getImgUrl() {
        return imgUrl;
    }


    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    

}
