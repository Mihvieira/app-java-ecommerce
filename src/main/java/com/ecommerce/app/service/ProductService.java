package com.ecommerce.app.service;

import com.ecommerce.app.dto.CategoryDTO;
import com.ecommerce.app.dto.OrderDTO;
import com.ecommerce.app.dto.ProductCategoryDTO;
import com.ecommerce.app.dto.ProductDTO;
import com.ecommerce.app.entities.Category;
import com.ecommerce.app.entities.Order;
import com.ecommerce.app.entities.Product;
import com.ecommerce.app.entities.User;
import com.ecommerce.app.repository.CategoryRepository;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.service.exceptions.DatabaseException;
import com.ecommerce.app.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;

    public List<ProductCategoryDTO> findAll() {
        List<Product> products = repository.findAll();
        return products.stream()
                .map(product -> {
                    Set<Category> categories = product.getCategories();
                    List<String> categoryNames = categories.stream()
                            .map(Category::getName)
                            .collect(Collectors.toList());
                    ProductCategoryDTO dto = new ProductCategoryDTO(product.getId(), product.getName(),
                            product.getDescription(), product.getPrice(), categoryNames);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public ProductDTO findById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return new ProductDTO(product);
    }

    @Transactional
    public ProductCategoryDTO insert(ProductCategoryDTO product) {
        validateProductDTO(product);
        try {
            Product entity = new Product();
            if (product.getId() != null) {
                entity.setId(product.getId());
            }
            List<String> categoryNames = new ArrayList<>();
            // verificar se a categoria existe e se está contida em product
            for (String categoryName : product.getCategory()) {
                CategoryDTO category = categoryService.findByName(categoryName);
                Category cat = new Category(category.getId(), category.getName());
                entity.getCategories().add(cat);
                categoryNames.add(categoryName);
            }
            entity.setName(product.getName());
            entity.setPrice(product.getPrice());
            entity.setDescription(product.getDescription());
            entity.setImgUrl(product.getImgUrl());
            Product savProduct = repository.save(entity);
            return new ProductCategoryDTO(savProduct.getId(), savProduct.getName(), savProduct.getDescription(),
                    savProduct.getPrice(), categoryNames);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database integrity violation: " + e.getMessage());
        } catch (HttpMessageNotReadableException e) {
            throw new RuntimeException("Invalid message format: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error: " + e.getMessage());
        }
    }

    private void validateProductDTO(ProductCategoryDTO dados) {
        if (dados.getCategory() == null) {
            throw new IllegalArgumentException("Category name cannot be null");
        }
        if (dados.getName() == null) {
            throw new IllegalArgumentException("Product name cannot be null");
        }
        if (dados.getPrice() == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if (!existsCategoryByName(dados.getCategory())) {
            throw new IllegalArgumentException("Invalid Category name");
        }
    }

    private boolean existsCategoryByName(List<String> names){
        List<Boolean> resuList = new ArrayList<>();
        for (String name : names) {
            var result = categoryService.existsCategoryByName(name);
            resuList.add(result);
        }
        return resuList.stream().allMatch(Boolean::booleanValue);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database integrity violation: " + e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
