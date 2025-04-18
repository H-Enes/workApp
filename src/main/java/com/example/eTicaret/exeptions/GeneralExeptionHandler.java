package com.example.eTicaret.exeptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GeneralExeptionHandler {

	@ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Beklenmeyen bir hata oluştu.";

        if (ex instanceof ProductNotFoundException) {
        	status = HttpStatus.NOT_FOUND;
            message = ex.getMessage();
        } else if (ex instanceof StockNotEnoughException) {
        	status = HttpStatus.BAD_REQUEST;
            message = ex.getMessage();
        } else if (ex instanceof ProductAlreadyExistsException) {
        	status = HttpStatus.CONFLICT;
            message = ex.getMessage();
        } else if (ex instanceof IllegalArgumentException) {
        	status = HttpStatus.BAD_REQUEST;
            message = ex.getMessage();			
        }
        
        BaseErrorResponse response = new BaseErrorResponse(
        	message,
        	status.value(),
        	LocalDateTime.now(),
        	request.getRequestURI()		
        );

        return new ResponseEntity<>(response, status);
    }
}
