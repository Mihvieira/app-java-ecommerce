package com.ecommerce.app.service;

import com.ecommerce.app.entities.Payment;
import com.ecommerce.app.repository.PaymentRepository;
import com.ecommerce.app.service.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public List<Payment> findAll(){
        return repository.findAll();
    }

    public Payment findById(Long id) {
        Optional<Payment> obj = repository.findById(id);
        return obj.get();
    }

    public Payment insert(Payment obj){
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

    public Payment update(Long id, Payment obj){
        Payment entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    public void updateData(Payment entity, Payment obj){
        entity.setMoment(obj.getMoment());
        entity.setOrder(obj.getOrder());
    }
}
