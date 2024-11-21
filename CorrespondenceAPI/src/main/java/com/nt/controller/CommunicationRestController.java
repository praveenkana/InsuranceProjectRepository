package com.nt.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommunicationRestController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllExceptions(Exception exception) {

		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<String> handleFIleNotFoundExceptions(FileNotFoundException exception) {

		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> handleIOExceptions(IOException exception){
		
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
}