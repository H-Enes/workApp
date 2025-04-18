package com.example.eTicaret.exeptions;

public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(String message) {
        super(message);
    }
}
