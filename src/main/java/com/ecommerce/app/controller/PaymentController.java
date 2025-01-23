package com.ecommerce.app.controller;

import com.ecommerce.app.dto.PaymentDTO;
import com.ecommerce.app.service.PaymentService;
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
import com.ecommerce.app.entities.Payment;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> findAll(){
        List<PaymentDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable Long id){
        PaymentDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> insert(@RequestBody Payment obj){
        PaymentDTO entity =  service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @DeleteMapping(value="{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable Long id, @RequestBody Payment obj){
        var entity = service.update(id, obj);
        return ResponseEntity.ok().body(entity);
    }
}
