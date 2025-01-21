package com.ecommerce.app.controller;

import com.ecommerce.app.dto.OrderItemProductDTO;
import com.ecommerce.app.dto.OrderUserDTO;
import com.ecommerce.app.entities.Order;
import com.ecommerce.app.entities.OrderItem;
import com.ecommerce.app.service.OrderItemService;
import com.ecommerce.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="{id}")
    public ResponseEntity<Optional<Order>> findById(@PathVariable Long id){
        Optional<Order> obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "users/{id_client}")
    public ResponseEntity<List<OrderUserDTO>> getOrdersByClient(@PathVariable Long id_client) {
        List<OrderUserDTO> obj = service.findOrdersByClient(id_client);
        return ResponseEntity.ok().body(obj);
    }
    
    @GetMapping(value = "items/{id_order}")
    public ResponseEntity<List<OrderItemProductDTO>> getAllOrdersItems(@PathVariable Long id) {
        List<OrderItemProductDTO> obj = orderItemService.findAllOrderItems(id);
        return ResponseEntity.ok().body(obj);
    }
}
