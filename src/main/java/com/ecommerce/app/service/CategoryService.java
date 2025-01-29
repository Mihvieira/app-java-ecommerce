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
import org.springframework.data.domain.Sort;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryDTO> findAll() {
        List<Category> entity = repository.findAll();
        return entity.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    public CategoryDTO findById(Long id) {
        Category entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return new CategoryDTO(entity);
    }

    public CategoryDTO findByName(String name) {
        Category entity = repository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(name));

        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(Category dados) {
        try {
            Category entity = new Category();
            if (dados.getId() != null) {
                entity.setId(dados.getId());
            }
            entity.setName(dados.getName());
            return new CategoryDTO(repository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(dados.getId());
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database integrity violation: " + e.getMessage());
        } catch (HttpMessageNotReadableException e) {
            throw new RuntimeException("Invalid message format: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error: " + e.getMessage());
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public long countCategories() {
        return repository.count();
    }

    public boolean existsCategory(Long id) {
        return repository.existsById(id);
    }

    public boolean existsCategoryByName(String name) {
        return repository.findByName(name).isPresent();
    }

    public List<CategoryDTO> findAllSortedByName() {
        List<Category> entity = repository.findAll(Sort.by("name"));
        return entity.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

}
