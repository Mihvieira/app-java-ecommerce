package com.ecommerce.app.service;

import com.ecommerce.app.dto.OrderDTO;
import com.ecommerce.app.dto.ProductDTO;
import com.ecommerce.app.entities.Order;
import com.ecommerce.app.entities.Product;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.service.exceptions.DatabaseException;
import com.ecommerce.app.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> findAll(){
        List<Product> entity = repository.findAll();
        return entity.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
    }

    public ProductDTO findById(Long id) {
        Optional<Product> entity = repository.findById(id);
        if (entity.isPresent()) {
            return new ProductDTO(entity.get());
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public ProductDTO insert(Product obj){
        try {
            Product entity = repository.save(obj);
            return new ProductDTO(entity);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ProductDTO update(Long id, Product obj){
        try {
            Product entity = repository.getReferenceById(id);
            updateData(entity, obj);
            Product savedEntity = repository.save(entity);
            return new ProductDTO(savedEntity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);   
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void updateData(Product entity, Product obj){
        entity.setName(obj.getName());
        entity.setPrice(obj.getPrice());
        entity.setDescription(obj.getDescription());
        entity.setImgUrl(obj.getImgUrl());
        entity.setCategories(obj.getCategories());
    }
}
