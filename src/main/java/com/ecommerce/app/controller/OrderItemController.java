package com.ecommerce.app.controller;

import java.net.URI;
import java.util.List;

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

import com.ecommerce.app.dto.OrderItemDTO;
import com.ecommerce.app.dto.OrderItemProductDTO;
import com.ecommerce.app.entities.OrderItem;
import com.ecommerce.app.service.OrderItemService;

@RestController
@RequestMapping(value = "/order-item")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItem() {
        List<OrderItemDTO> obj = orderItemService.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "detail/{id}")
    public ResponseEntity<List<OrderItemProductDTO>> getAllOrdersItems(@PathVariable Long id) {
        List<OrderItemProductDTO> obj = orderItemService.findAllOrderItems(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "product/{id}")
    public ResponseEntity<List<OrderItemDTO>> getAllOrdersByproduct(@PathVariable Long id) {
        List<OrderItemDTO> obj = orderItemService.findByProduct(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "order/{id}")
    public ResponseEntity<List<OrderItemDTO>> getAllOrdersByOrder(@PathVariable Long id) {
        List<OrderItemDTO> obj = orderItemService.findByOrder(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<OrderItemDTO> insert(@RequestBody OrderItem obj){
        OrderItemDTO entity =  orderItemService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId_order()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @DeleteMapping(value="{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="{id}")
    public ResponseEntity<List<OrderItemDTO>> update(@PathVariable Long id, @RequestBody OrderItem obj){
        var entity = orderItemService.update(id, obj);
        return ResponseEntity.ok().body(entity);
    }
}
