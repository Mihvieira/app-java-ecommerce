package com.ecommerce.app.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ecommerce.app.dto.CategoryDTO;
import com.ecommerce.app.dto.OrderDTO;
import com.ecommerce.app.dto.OrderItemDTO;
import com.ecommerce.app.dto.OrderItemProductDTO;
import com.ecommerce.app.entities.Category;
import com.ecommerce.app.entities.Order;
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

    @Transactional
    public OrderItemDTO insert(OrderItem obj){
         try {
            OrderItem entity = repository.save(obj);
            return new OrderItemDTO(entity);
        } catch (RuntimeException e) {
            throw e;
        }
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


    public List<OrderItemProductDTO> findAllOrderItems(Long id){
        try {
            List<OrderItemProductDTO> list = repository.findAllOrderItens(id);
            return list;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteByOrder(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public List<OrderItemDTO> update(Long id, OrderItem obj) {
        try {
            List<OrderItem> entity = repository.findByIdOrder(id);
            entity.forEach(x -> updateData(x, obj));
            List<OrderItemDTO> savedEntities = entity.stream()
                .map(repository::save)
                .map(OrderItemDTO::new)
                .collect(Collectors.toList());
            return savedEntities;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateData(OrderItem entity, OrderItem obj) {
        entity.setPrice(obj.getPrice());
        entity.setQuantity(obj.getQuantity());
        entity.setProduct(obj.getProduct());
        entity.setOrder(obj.getOrder());
    }

}
