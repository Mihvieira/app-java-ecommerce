package com.ecommerce.app.controller;

import com.ecommerce.app.dto.ProductDTO;
import com.ecommerce.app.entities.Product;
import com.ecommerce.app.service.ProductService;
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

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping //fail categories
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<ProductDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="{id}")//fail - categories
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        ProductDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping 
    public ResponseEntity<ProductDTO> insert(@RequestBody Product obj){
        ProductDTO product =  service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @DeleteMapping(value="{id}") // fail violation constraint
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody Product obj){
        var product = service.update(id, obj);
        return ResponseEntity.ok().body(product);
    }
}
