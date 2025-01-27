package com.ecommerce.app.controller;

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
import com.ecommerce.app.dto.OrderItemDTO;
import com.ecommerce.app.dto.OrderItemProductDTO;
import com.ecommerce.app.service.OrderItemService;

/**
 * OrderItemController is a REST controller that handles HTTP requests for managing order items.
 * It provides endpoints to perform CRUD operations on order items.
 * 
 * Endpoints:
 * - GET /order-item: Retrieve all order items.
 * - GET /order-item/detail/{id}: Retrieve all order items with product details for a specific order.
 * - GET /order-item/product/{id}: Retrieve all order items for a specific product.
 * - GET /order-item/order/{id}: Retrieve all order items for a specific order.
 * - POST /order-item: Insert a new order item.
 * - DELETE /order-item/{id}: Delete an order item by its ID.
 * - PUT /order-item/{order_id}-{product_id}: Update an existing order item by order ID and product ID.
 * 
 * This controller uses OrderItemService to perform the business logic.
 * 
 * @see com.ecommerce.app.service.OrderItemService
 * @see com.ecommerce.app.dto.OrderItemDTO
 * @see com.ecommerce.app.dto.OrderItemProductDTO
 */

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
    public ResponseEntity<Void> insert(@RequestBody OrderItemDTO obj){
        orderItemService.insert(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="{order_id}-{product_id}")
    public ResponseEntity<OrderItemDTO> update(@PathVariable("order_id") Long order_id, @PathVariable("product_id") Long product_id, @RequestBody OrderItemDTO obj){
        OrderItemDTO entity = orderItemService.update(order_id, product_id, obj);
        return ResponseEntity.ok().body(entity);
    }
}
