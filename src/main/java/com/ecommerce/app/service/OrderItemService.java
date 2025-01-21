package com.ecommerce.app.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ecommerce.app.dto.OrderItemDTO;
import com.ecommerce.app.dto.OrderItemProductDTO;
import com.ecommerce.app.dto.OrderUserDTO;
import com.ecommerce.app.entities.OrderItem;
import com.ecommerce.app.repository.OrderItemRepository;
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

    @Transactional(readOnly = true)
    public List<OrderItemDTO> findByIdPrice(Double price) {
        List<OrderItem> list = repository.findByPrice(price);
        return list.stream().map( x -> new OrderItemDTO(x)).collect(Collectors.toList());
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

    public void updateData(OrderItem entity, OrderItem obj){
        entity.setId(obj.getId());
        entity.setPrice(obj.getPrice());
        entity.setQuantity(obj.getQuantity());
    }

    @Transactional(readOnly = true)
    public List<OrderItemProductDTO> findAllOrderItems(Long id){
        try {
            List<OrderItemProductDTO> list = repository.findAllOrderItems(id);
            return list;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}
