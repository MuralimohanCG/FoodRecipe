package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FoodItemExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<FoodItemErrorResponse> handleException(FoodItemNotFoundException fie){
		FoodItemErrorResponse errorResponse = new FoodItemErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(fie.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<FoodItemErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler
	public ResponseEntity<FoodItemErrorResponse> handleException(Exception ex){
		FoodItemErrorResponse errorResponse = new FoodItemErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<FoodItemErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
