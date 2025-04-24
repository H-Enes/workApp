package com.example.eTicaret.exeptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) //@Valid anatasyonu olan yerde hata olursa çalışır
    public ResponseEntity<BaseErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> messages = ex.getAllErrors().stream() // validasyon hatalarını alır
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        String combinedMessage = String.join(", ", messages); //tüm hata mesajlarını birleştirir

        BaseErrorResponse error = new BaseErrorResponse(  //hata cevabı döner
                combinedMessage,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ConstraintViolationException.class) //@Min, @Max gibi parametre validasyon hatalarını yakalar.
    public ResponseEntity<BaseErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        String message = ex.getConstraintViolations()
                           .stream()
                           .map(violation -> violation.getMessage())
                           .collect(Collectors.joining(", "));

        return buildErrorResponse(message, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<BaseErrorResponse> productAlreadyExistsExceptionHandler(ProductAlreadyExistsException ex, HttpServletRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<BaseErrorResponse> productNotFoundExceptionHandler(ProductNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ProductIllegalArgumentException.class)
    public ResponseEntity<BaseErrorResponse> productIllegalArgumentExceptionHandler(ProductIllegalArgumentException ex, HttpServletRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(StockNotEnoughException.class)
    public ResponseEntity<BaseErrorResponse> stockNotEnoughExceptionHandler(StockNotEnoughException ex, HttpServletRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ShoppingCartItemNotFoundException.class)
    public ResponseEntity<BaseErrorResponse> handleShoppingCartItemNotFound(ShoppingCartItemNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorResponse> handleGeneralException(Exception ex, HttpServletRequest request) {
        return buildErrorResponse("Bilinmeyen bir hata oluştu.", HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<BaseErrorResponse> buildErrorResponse(String message, HttpStatus status, HttpServletRequest request) {
        BaseErrorResponse error = new BaseErrorResponse(
                message,
                status.value(),
                LocalDateTime.now(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, status);
    }
}
