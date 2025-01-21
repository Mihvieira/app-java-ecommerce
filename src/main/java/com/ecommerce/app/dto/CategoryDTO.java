package com.ecommerce.app.dto;

import java.util.HashSet;
import java.util.Set;

import com.ecommerce.app.entities.Product;

public class CategoryDTO {

    private Long id;
    private String name;
    private Set<Product> products = new HashSet<>();
    
    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    

}
