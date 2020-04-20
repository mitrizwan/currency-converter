package com.techfynder.currency.advice;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.techfynder.currency.exception.CurrencyNotFoundException;
import com.techfynder.currency.validation.ErrorDetails;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
//  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
	  Map<String, Object> body = new LinkedHashMap<>();
      body.put("timestamp", new Date());
      body.put("status", status.value());
      
     

      //Get all fields errors
      List<String> errors = ex.getBindingResult()
              .getFieldErrors()
              .stream()
              .map(x -> x.getDefaultMessage())
              .collect(Collectors.toList());
      
//      ErrorDetails errorDetails=new ErrorDetails(new Date(), "Requ);

      body.put("errors", errors);

      return new ResponseEntity<>(body, headers, status);
  } 
  
  @ExceptionHandler(CurrencyNotFoundException.class)
  public ResponseEntity<ErrorDetails> customHandleNotFound(Exception ex, WebRequest request) {

      ErrorDetails errors = new ErrorDetails();
      errors.setTimestamp(LocalDateTime.now());
      errors.setError(ex.getMessage());
      errors.setStatus(HttpStatus.NOT_FOUND.value());

      return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

  }
   
}