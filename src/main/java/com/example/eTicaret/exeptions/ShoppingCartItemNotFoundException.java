package com.example.eTicaret.exeptions;

public class ShoppingCartItemNotFoundException extends RuntimeException {
    public ShoppingCartItemNotFoundException(String message) {
        super(message);
    }
}
