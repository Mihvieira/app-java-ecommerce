package com.ecommerce.app.service;

import com.ecommerce.app.dto.PaymentDTO;
import com.ecommerce.app.entities.Order;
import com.ecommerce.app.entities.Payment;
import com.ecommerce.app.repository.OrderRepository;
import com.ecommerce.app.repository.PaymentRepository;
import com.ecommerce.app.service.exceptions.DatabaseException;
import com.ecommerce.app.service.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;
    @Autowired
    private OrderRepository orderRepository;

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

    public PaymentDTO insert(PaymentDTO obj){
        try {
            Payment entity = new Payment();
            Order order = orderRepository.findById(obj.getOrder_id())
                    .orElseThrow(() -> new ResourceNotFoundException(obj.getOrder_id()));
            if (obj.getId() != null){
                entity.setId(obj.getId());
            }
            entity.setOrder(order);
            entity.setMoment(obj.getMoment());
            // Associar o pagamento salvo ao pedido
            order.setPayment(Arrays.asList(entity));
            orderRepository.save(order);
            return new PaymentDTO(order.getPayment().getLast());
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database integrity violation: " + e.getMessage());
        } catch (HttpMessageNotReadableException e) {
            throw new RuntimeException("Invalid message format: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error: " + e.getMessage());
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
    
}
