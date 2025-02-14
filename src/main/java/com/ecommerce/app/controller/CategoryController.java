package com.ecommerce.app.controller;

import com.ecommerce.app.dto.CategoryDTO;
import com.ecommerce.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import com.ecommerce.app.entities.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
        CategoryDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value="/count")
    public ResponseEntity<Long> countCategories(){
        Long obj = service.countCategories();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value="/exists/{id}")
    public ResponseEntity<Boolean> existsCategoryById(@PathVariable Long id){
        Boolean obj = service.existsCategory(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value="/exists/{name}")
    public ResponseEntity<Boolean> existsCategoryByName(@PathVariable String name){
        Boolean obj = service.existsCategoryByName(name);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value="/name")
    public ResponseEntity<List<CategoryDTO>> findAllSortedByName(){
        List<CategoryDTO> obj = service.findAllSortedByName();
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody Category obj){
        CategoryDTO entity =  service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @DeleteMapping(value="{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(@RequestBody Category obj){
        var entity = service.insert(obj);
        return ResponseEntity.ok().body(entity);
    }
}
