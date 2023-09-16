package dev.ganeshpc.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.ganeshpc.productservice.dtos.ErrorDto;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException e) {

        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND, e.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}
