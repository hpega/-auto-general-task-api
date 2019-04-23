package com.auto.task.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Rest Advice for pretty-printed JSON when errors are thrown
 *
 */
@RestControllerAdvice
public class ExceptionProcessor extends ResponseEntityExceptionHandler{
	
	/**
	 * Add default headers for the response
	 */
	public ExceptionProcessor(){
		headers.add("Content-Type", "application/json");
	}
	
	HttpHeaders headers = new HttpHeaders();
	
	@ExceptionHandler(value= {ValidationException.class})
	   public ResponseEntity<Object> handleEntityNotFound(
			   ValidationException ex) {
		ResponseEntity<Object> response = 
				new ResponseEntity<>(ex.errorSpecific, headers, HttpStatus.BAD_REQUEST);
		return response;
	}
	
	@ExceptionHandler(value= {NotFoundException.class})
	   public ResponseEntity<Object> handleEntityNotFound(
			   NotFoundException ex) {
		return new ResponseEntity<Object>(ex.getDetails(), headers, HttpStatus.NOT_FOUND);
	}
}
