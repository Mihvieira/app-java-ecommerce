package com.ecommerce.app.service;

import com.ecommerce.app.entities.Product;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.service.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }

    public Product insert(Product obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public Product update(Long id, Product obj){
        Product entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    public void updateData(Product entity, Product obj){
        entity.setName(obj.getName());
        entity.setPrice(obj.getPrice());
        entity.setDescription(obj.getDescription());
        entity.setImgUrl(obj.getImgUrl());
        entity.setCategories(obj.getCategories());
    }
}
