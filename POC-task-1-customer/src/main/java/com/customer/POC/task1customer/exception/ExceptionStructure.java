package com.customer.POC.task1customer.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ExceptionStructure {

    private Date timestamp;
    private String message;
    private List<String> errors;
    
    public ExceptionStructure(Date timestamp, String message, String error) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
	
	
	
	/*
	 * public Date getTimestamp() { return timestamp; }
	 * 
	 * public String getMessage() { return message; }
	 * 
	 * public String getDetails() { return details; }
	 * 
	 * @Override public String toString() { return "ExceptionsHandler [timestamp=" +
	 * timestamp + ", message=" + message + ", details=" + details + "]"; } public
	 * ExceptionStructure(Date timestamp, String message, String details) { super();
	 * this.timestamp = timestamp; this.message = message; this.details = details; }
	 * 
	 * public ExceptionStructure() {}
	 */


