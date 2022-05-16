package com.example.demo.exception;

@SuppressWarnings("serial")
public class FoodItemNotFoundException extends RuntimeException {
	
	public FoodItemNotFoundException(String message) {
        super(message);
    }

    public FoodItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FoodItemNotFoundException(Throwable cause) {
        super(cause);
    }
}
