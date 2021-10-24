package com.customer.POC.task1customer.exception;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	  MethodArgumentNotValidException ex, 
	  HttpHeaders headers, 
	  HttpStatus status, 
	  WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        errors.add(error.getField() + ": " + error.getDefaultMessage());
	    }
	    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	    }
	    
	    ExceptionStructure apiError = new ExceptionStructure(ZonedDateTime.now(), "Validation failed", errors);
	    return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
	}
	 
	
	
	   @ExceptionHandler({Exception.class})
	   public final ResponseEntity<ExceptionStructure>handleAllExceptions(Exception ex,
	  WebRequest request){
		   List<String> errors = new ArrayList<String>();
		   errors.add(request.getDescription(false));
		   ExceptionStructure exception=new ExceptionStructure(ZonedDateTime.now(),ex.getMessage(),errors);
	  return new ResponseEntity(exception,HttpStatus.BAD_REQUEST); //just to show our customized Exception response
	  }
	 	 
	
	
}
