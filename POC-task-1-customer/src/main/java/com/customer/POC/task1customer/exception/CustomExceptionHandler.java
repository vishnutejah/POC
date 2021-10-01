package com.customer.POC.task1customer.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	
	  @ExceptionHandler(Exception.class) 
	  public final ResponseEntity<Object>handleAllExceptions(Exception ex, WebRequest request){ 
		  ExceptionStructure exception=new ExceptionStructure(new Date(),ex.getMessage(),request.getDescription(false)); 
	  return new ResponseEntity(exception,HttpStatus.BAD_REQUEST); //just to show our customized Exception response
	  }
	 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionStructure exception=new ExceptionStructure(new Date(),"Validation Failed",ex.getBindingResult().getFieldErrors().toString());
		return new ResponseEntity(exception,HttpStatus.BAD_REQUEST);
	}
	
}
