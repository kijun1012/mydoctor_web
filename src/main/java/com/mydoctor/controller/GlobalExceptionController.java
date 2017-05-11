package com.mydoctor.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mydoctor.exception.ErrorResponse;
import com.mydoctor.exception.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionController {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> 
		handleUserNotFoundException(HttpServletRequest req, UserNotFoundException ex) {
		
		//URLÀÌ ³Ñ¾î¿È
		String requestURL = req.getRequestURL().toString();
		ErrorResponse errorResponse = new ErrorResponse();
		
		
		errorResponse.setRequestURL(requestURL);
		errorResponse.setErrorCode("user.notfound.exception");
		errorResponse.setErrorMsg("User with id " + ex.getUserName() + " not found");
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
}