package com.ecommerce.app.service;

import com.ecommerce.app.dto.UserDTO;
import com.ecommerce.app.dto.UserMinDTO;
import com.ecommerce.app.entities.User;
import com.ecommerce.app.repository.UserRepository;
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
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserMinDTO> findAll(){
        List<User> entity = repository.findAll();
        return entity.stream().map(x -> new UserMinDTO(x)).collect(Collectors.toList());
    }

    public UserMinDTO findById(Long id){
        Optional<User> entity = repository.findById(id);
        if (entity.isPresent()) {
            return new UserMinDTO(entity.get());
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    public UserDTO insert(User obj){
        try {
            User entity = repository.save(obj);
            return new UserDTO(entity);
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

    public UserDTO update(Long id, User obj){
        try {
            User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            updateData(entity, obj);
            User savedEntity = repository.save(entity);
            return new UserDTO(savedEntity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);   
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void updateData(User entity, User obj){
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }

}
