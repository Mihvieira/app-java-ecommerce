package com.ecommerce.app.service;

import com.ecommerce.app.dto.OrderDTO;
import com.ecommerce.app.dto.OrderUserDTO;
import com.ecommerce.app.entities.Order;
import com.ecommerce.app.entities.User;
import com.ecommerce.app.repository.OrderRepository;
import com.ecommerce.app.repository.UserRepository;
import com.ecommerce.app.service.exceptions.DatabaseException;
import com.ecommerce.app.service.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private UserRepository userRepository;

    public List<OrderDTO> findAll() {
        List<Order> entity = repository.findAll();
        return entity.stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    public OrderDTO findById(Long id) {
        Order entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return new OrderDTO(entity);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dados) {
        validateOrderDTO(dados);
        try {
            Order entity = new Order();
            User client = userRepository.findById(dados.getClientId())
                    .orElseThrow(() -> new ResourceNotFoundException(dados.getClientId()));
            if (dados.getId() != null) {
                entity.setId(dados.getId());
            }
            entity.setClient(client);
            entity.setMoment(dados.getMoment());
            entity.setOrderStatus(dados.getOrderStatus());
            Order savOrder = repository.save(entity);
            return new OrderDTO(savOrder);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database integrity violation: " + e.getMessage());
        } catch (HttpMessageNotReadableException e) {
            throw new RuntimeException("Invalid message format: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error: " + e.getMessage());
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database integrity violation: " + e.getMessage());
        }
    }

    private void validateOrderDTO(OrderDTO dados) {
        if (dados.getClientId() == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        if (dados.getMoment() == null) {
            throw new IllegalArgumentException("Order moment cannot be null");
        }
        if (dados.getOrderStatus() == null) {
            throw new IllegalArgumentException("Order status cannot be null");
        }
    }

    public List<OrderUserDTO> findOrdersByClient(Long client_id) {
        try {
            return repository.findOrdersByClient(client_id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(client_id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
