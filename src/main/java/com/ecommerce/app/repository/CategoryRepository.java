package com.ecommerce.app.repository;

import com.ecommerce.app.entities.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.name = UPPER(:name)")
    Optional<Category> findByName(@Param("name") String name);

    boolean existsCategoryByName(String name);
}
