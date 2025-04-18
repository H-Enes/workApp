package com.example.eTicaret.exeptions;

public class ProductAlreadyExistsException extends RuntimeException {
	public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
