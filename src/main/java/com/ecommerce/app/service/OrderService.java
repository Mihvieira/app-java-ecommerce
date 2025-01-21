package com.ecommerce.app.service;

import com.ecommerce.app.dto.OrderUserDTO;
import com.ecommerce.app.entities.Order;
import com.ecommerce.app.repository.OrderRepository;
import com.ecommerce.app.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.get();
    }

    @Transactional
    public Order insert(Order obj){
        return repository.save(obj);
    }

    @Transactional
    public void delete(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public Order update(Long id, Order obj){
        Order entity = new Order();
        try {
            entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
            
        } catch (RuntimeException e) {
            e.printStackTrace();
            return entity;
        }
            
    }

    @Transactional
    public void updateData(Order entity, Order obj){
        entity.setClient(obj.getClient());
        entity.setMoment(obj.getMoment());
        entity.setOrderStatus(obj.getOrderStatus());
    }

    public List<OrderUserDTO> findOrdersByClient(Long client_id){
        try {
            List<OrderUserDTO> listOusers = repository.findOrdersByClient(client_id);
            return listOusers;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(client_id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
