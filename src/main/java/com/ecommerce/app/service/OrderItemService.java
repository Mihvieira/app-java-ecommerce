package com.ecommerce.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ecommerce.app.dto.OrderItemDTO;
import com.ecommerce.app.dto.OrderItemProductDTO;
import com.ecommerce.app.entities.OrderItem;
import com.ecommerce.app.entities.User;
import com.ecommerce.app.repository.OrderItemRepository;
import com.ecommerce.app.service.exceptions.DatabaseException;
import com.ecommerce.app.service.exceptions.ResourceNotFoundException;

import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;


    @Transactional(readOnly = true)
    public List<OrderItemDTO> findAll(){
        List<OrderItem> list = repository.findAll();
        return list.stream().map( x -> new OrderItemDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderItemDTO> findByProduct(Long id) {
        List<OrderItem> list = repository.findByIdProduct(id);
        return list.stream().map( x -> new OrderItemDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderItemDTO> findByOrder(Long id) {
        List<OrderItem> list = repository.findByIdOrder(id);
        return list.stream().map( x -> new OrderItemDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public void insert(OrderItemDTO dados){
        validateOrderItemDTO(dados);
         try {
            repository.saveItens(dados.getOrder_id(), dados.getProduct_id(), dados.getQuantity(), dados.getPrice());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void deleteByOrder(Long id){
        try {
            repository.deleteByOrder(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public List<OrderItemProductDTO> findAllOrderItems(Long id){
        try {
            List<OrderItemProductDTO> list = repository.findAllOrderItens(id);
            return list;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteByOrder(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void validateOrderItemDTO(OrderItemDTO dados) {
        if (dados.getOrder_id() == null) {
            throw new IllegalArgumentException("Order Id cannot be null");
        }
        if (dados.getProduct_id() == null) {
            throw new IllegalArgumentException("Product Id cannot be null");
        }
        if (dados.getPrice() == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if (dados.getQuantity() == null) {
            dados.setQuantity(1);
        }
    }

    @Transactional
    public OrderItemDTO update(OrderItemDTO dados) {
        try {
            OrderItem entity = repository.findByIdOrderAndProduct(dados.getOrder_id(), dados.getProduct_id());
            if (entity == null) {
                throw new ResourceNotFoundException(dados.getOrder_id());
            }
            updateData(entity, dados);
            entity = repository.save(entity);
            return new OrderItemDTO(entity);
        } catch (NullPointerException e) {
            throw new RuntimeException(e.getMessage());  
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateData(OrderItem entity, OrderItemDTO dados) {
        entity.getId().getOrder().setId(dados.getOrder_id());
        entity.getId().getProduct().setId(dados.getProduct_id());
        entity.setPrice(dados.getPrice());
        entity.setQuantity(dados.getQuantity());
    }

}
