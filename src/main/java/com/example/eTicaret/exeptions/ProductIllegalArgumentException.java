package com.example.eTicaret.exeptions;

public class ProductIllegalArgumentException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ProductIllegalArgumentException(String message) {
        super(message);
    }
}
