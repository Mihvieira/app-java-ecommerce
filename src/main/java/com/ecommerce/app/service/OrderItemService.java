package com.ecommerce.app.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ecommerce.app.dto.OrderUserDTO;
import com.ecommerce.app.entities.OrderItem;
import com.ecommerce.app.repository.OrderItemRepository;
import com.ecommerce.app.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    public List<OrderItem> findAll(){
        return repository.findAll();
    }

    public OrderItem findByIdProduct(Long id) {
        Optional<OrderItem> obj = repository.findByIdProduct(id);
        return obj.get();
    }

    public OrderItem findByIdOrder(Long id) {
        Optional<OrderItem> obj = repository.findByIdOrder(id);
        return obj.get();
    }

    public OrderItem findByIdPrice(Long id) {
        Optional<OrderItem> obj = repository.findByIdOrder(id);
        return obj.get();
    }

    @Transactional
    public OrderItem insert(OrderItem obj){
        return repository.save(obj);
    }

    @Transactional
    public void deleteByOrder(Long id){
        try {
            repository.deleteByOrder(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public OrderItem update(Long id_order, OrderItem obj){
        OrderItem entity = new OrderItem();
        try {
            entity = repository.getReferenceByOrderId(id_order);
            updateData(entity, obj);
            return repository.save(entity);
            
        } catch (RuntimeException e) {
            e.printStackTrace();
            return entity;
        }
            
    }

    @Transactional
    public void updateData(OrderItem entity, OrderItem obj){
        entity.setId(obj.getId());
        entity.setPrice(obj.getPrice());
        entity.setQuantity(obj.getQuantity());
    }

    public List<OrderUserDTO> findAllOrderItems(Long client_id){
        try {
            List<OrderUserDTO> listOusers = repository.findAllOrderItems(client_id);
            return listOusers;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(client_id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}
