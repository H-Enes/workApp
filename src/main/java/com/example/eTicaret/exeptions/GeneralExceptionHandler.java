package com.example.eTicaret.exeptions;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        List<String> messages = new ArrayList<>();
        ex.getAllErrors().forEach(err -> messages.add(err.getDefaultMessage()));
        errors.put("messages", messages);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<?> productAlreadyExistsExceptionHandler(ProductAlreadyExistsException ex) {
        return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> productNotFoundExceptionHandler(ProductNotFoundException ex) {
        return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductIllegalArgumentException.class)
    public ResponseEntity<?> productIllegalArgumentExceptionHandler(ProductIllegalArgumentException ex) {
        return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StockNotEnoughException.class)
    public ResponseEntity<?> stockNotEnoughExceptionHandler(StockNotEnoughException ex) {
        return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ShoppingCartItemNotFoundException.class)
    public ResponseEntity<?> handleShoppingCartItemNotFound(ShoppingCartItemNotFoundException ex) {
        return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
    	ex.printStackTrace();
        return new ResponseEntity<>(Map.of("message", "Bilinmeyen bir hata oluştu."), HttpStatus.BAD_REQUEST);
    }

    
}
