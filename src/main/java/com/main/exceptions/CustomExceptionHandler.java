package com.main.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	
	

	
	@ExceptionHandler
	public ResponseEntity<?> handleId(IDException ex){
		
		ExceptionResponseBody body = new ExceptionResponseBody();
		body.setTimeStamp(LocalDateTime.now());
		body.setErrorMessage(ex.getMessage());
		body.setErrorCode(400);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}
	
	@ExceptionHandler
	public ResponseEntity<?> handleJwtToken(JwtTokenException ex){
		
		ExceptionResponseBody body = new ExceptionResponseBody();
		body.setTimeStamp(LocalDateTime.now());
		body.setErrorMessage(ex.getMessage());
		body.setErrorCode(400);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
	}
	  @Override protected ResponseEntity<Object>
	  handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
	  headers, HttpStatus status, WebRequest request) {
	  
	  List<String> errors = new ArrayList<>(); for(FieldError
	  er:ex.getBindingResult().getFieldErrors()) {
	  errors.add(er.getDefaultMessage()); } for(ObjectError
	  ob:ex.getBindingResult().getGlobalErrors()) {
	  errors.add(ob.getDefaultMessage()); } ExceptionResponseBody ce = new
	  ExceptionResponseBody(); ce.setDetails(errors.toArray(new String[0])); return
	  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ce); }
	 
}
