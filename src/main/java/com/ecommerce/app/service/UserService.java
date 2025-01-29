package com.ecommerce.app.service;

import com.ecommerce.app.dto.OrderDTO;
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
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserMinDTO> findAll() {
        List<User> entity = repository.findAll();
        return entity.stream().map(x -> new UserMinDTO(x)).collect(Collectors.toList());
    }

    public UserMinDTO findById(Long id) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return new UserMinDTO(entity);
    }

    public UserDTO insert(User dados) {
        validateUserDTO(dados);
        try {
            User entity = new User();
            if (dados.getId() != null) {
                entity.setId(dados.getId());
            }
            entity.setName(dados.getName());
            entity.setEmail(dados.getEmail());
            entity.setPhone(dados.getPhone());
            entity.setPassword(dados.getPassword());
            return new UserDTO(repository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(dados.getId());
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database integrity violation: " + e.getMessage());
        } catch (HttpMessageNotReadableException e) {
            throw new RuntimeException("Invalid message format: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void validateUserDTO(User dados) {
        if (dados.getName() == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        if (dados.getEmail() == null) {
            throw new IllegalArgumentException("E-mail cannot be null");
        }
        if (dados.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
    }

}
