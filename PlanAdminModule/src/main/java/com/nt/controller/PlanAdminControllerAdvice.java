package com.nt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlanAdminControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllExceptions(Exception exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);

	}

}
