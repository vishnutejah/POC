package com.customer.POC.task1customer.exception;

import java.time.ZonedDateTime;
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

    private ZonedDateTime timestamp;
    private String message;
    private List<String> errors;
    
}


