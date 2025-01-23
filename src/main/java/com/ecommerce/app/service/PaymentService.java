package com.ecommerce.app.service;

import com.ecommerce.app.dto.PaymentDTO;
import com.ecommerce.app.entities.Payment;
import com.ecommerce.app.repository.PaymentRepository;
import com.ecommerce.app.service.exceptions.DatabaseException;
import com.ecommerce.app.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public List<PaymentDTO> findAll(){
        List<Payment> entity = repository.findAll();
        return entity.stream().map(x -> new PaymentDTO(x)).collect(Collectors.toList());
    }

    public PaymentDTO findById(Long id) {
        Optional<Payment> entity = repository.findById(id);
        if (entity.isPresent()) {
            return new PaymentDTO(entity.get());
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    public PaymentDTO insert(Payment obj){
        try {
            Payment entity = repository.save(obj);
            return new PaymentDTO(entity);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public PaymentDTO update(Long id, Payment obj){
        try {
            Payment entity = repository.getReferenceById(id);
            updateData(entity, obj);
            Payment savedEntity = repository.save(entity);
            return new PaymentDTO(savedEntity);
            
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id); 

        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void updateData(Payment entity, Payment obj){
        entity.setMoment(obj.getMoment());
        entity.setOrder(obj.getOrder());
    }
}
