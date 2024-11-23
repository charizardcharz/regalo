package net.arcatanium.regalo.controller;

import net.arcatanium.regalo.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resouceNotFound(){
        return new ResponseEntity<>("404 - Not found", HttpStatus.NOT_FOUND);
    }
}
