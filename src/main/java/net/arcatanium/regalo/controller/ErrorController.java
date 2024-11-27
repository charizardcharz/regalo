package net.arcatanium.regalo.controller;

import net.arcatanium.regalo.exception.ErrorDetails;
import net.arcatanium.regalo.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFound(ResourceNotFoundException ex, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(Instant.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
