package com.ecommerce.app.service;

import com.ecommerce.app.entities.Order;
import com.ecommerce.app.repository.OrderRepository;
import com.ecommerce.app.service.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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

    public Order insert(Order obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public Order update(Long id, Order obj){
        Order entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    public void updateData(Order entity, Order obj){
        entity.setClient(obj.getClient());
        entity.setMoment(obj.getMoment());
        entity.setOrderStatus(obj.getOrderStatus());
    }

}
