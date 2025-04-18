package com.example.eTicaret.exeptions;

public class StockNotEnoughException extends RuntimeException {
	public StockNotEnoughException(String message) {
        super(message);
    }
}
