package com.ecommerce.app.service;

import com.ecommerce.app.dto.CategoryDTO;
import com.ecommerce.app.entities.Category;
import com.ecommerce.app.repository.CategoryRepository;
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
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryDTO> findAll(){
        List<Category> entity = repository.findAll();
        return entity.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    public CategoryDTO findById(Long id) {
        Optional<Category> entity = repository.findById(id);
        if (entity.isPresent()) {
            return new CategoryDTO(entity.get());
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public CategoryDTO insert(Category obj){
        try {
            Category entity = repository.save(obj);
            return new CategoryDTO(entity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
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

    @Transactional
    public CategoryDTO update(Long id, Category obj){
        try {
            Category entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            updateData(entity, obj);
            Category savedEntity = repository.save(entity);
            return new CategoryDTO(savedEntity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);   
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateData(Category entity, Category obj){
        entity.setName(obj.getName());
    }

}
