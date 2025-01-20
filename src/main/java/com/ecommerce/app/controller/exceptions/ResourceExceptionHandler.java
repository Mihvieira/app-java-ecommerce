package com.ecommerce.app.controller.exceptions;

import com.ecommerce.app.service.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandartdError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource nor found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartdError err = new StandartdError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
