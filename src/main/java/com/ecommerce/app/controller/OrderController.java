package com.ecommerce.app.controller;

import com.ecommerce.app.dto.CategoryDTO;
import com.ecommerce.app.dto.OrderDTO;
import com.ecommerce.app.dto.OrderUserDTO;
import com.ecommerce.app.entities.Category;
import com.ecommerce.app.entities.Order;
import com.ecommerce.app.service.OrderService;
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
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;
    
    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        List<OrderDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
        OrderDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "users/{id_client}")
    public ResponseEntity<List<OrderUserDTO>> getOrdersByClient(@PathVariable Long id_client) {
        List<OrderUserDTO> obj = service.findOrdersByClient(id_client);
        return ResponseEntity.ok().body(obj);
    }
    
    @PostMapping
    public ResponseEntity<OrderDTO> insert(@RequestBody Order obj){
        OrderDTO entity =  service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @DeleteMapping(value="{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable Long id, @RequestBody Order obj){
        var entity = service.update(id, obj);
        return ResponseEntity.ok().body(entity);
    }
}
