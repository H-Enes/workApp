package com.example.eTicaret.exeptions;

public class StockNotEnoughException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public StockNotEnoughException(String message) {
        super(message);
    }
}
