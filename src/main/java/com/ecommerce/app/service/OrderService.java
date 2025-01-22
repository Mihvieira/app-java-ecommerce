package com.ecommerce.app.service;

import com.ecommerce.app.dto.OrderDTO;
import com.ecommerce.app.dto.OrderUserDTO;
import com.ecommerce.app.entities.Order;
import com.ecommerce.app.repository.OrderRepository;
import com.ecommerce.app.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<OrderDTO> findAll() {
        List<Order> entity = repository.findAll();
        return entity.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }

    public OrderDTO findById(Long id) {
        Optional<Order> entity = repository.findById(id);
        if (entity.isPresent()) {
            return new OrderDTO(entity.get());
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    @Transactional
    public OrderDTO insert(Order obj) {
        try {
            Order entity = repository.save(obj);
            return new OrderDTO(entity);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public OrderDTO update(Long id, Order obj) {
        try {
            Order entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            updateData(entity, obj);
            Order savedEntity = repository.save(entity);
            return new OrderDTO(savedEntity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateData(Order entity, Order obj) {
        entity.setClient(obj.getClient());
        entity.setMoment(obj.getMoment());
        entity.setOrderStatus(obj.getOrderStatus());
    }

    public List<OrderUserDTO> findOrdersByClient(Long client_id) {
        try {
            List<OrderUserDTO> listOusers = repository.findOrdersByClient(client_id);
            return listOusers;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(client_id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
