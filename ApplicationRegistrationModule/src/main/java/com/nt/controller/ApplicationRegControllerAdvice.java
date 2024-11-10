package com.nt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.service.IllegalIdValueException;

@RestControllerAdvice
public class ApplicationRegControllerAdvice {
	
	
	@ExceptionHandler(IllegalIdValueException.class)
	public ResponseEntity<String> handleInvalidIdValueException(IllegalIdValueException exception){
		
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllExceptions(Exception exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
