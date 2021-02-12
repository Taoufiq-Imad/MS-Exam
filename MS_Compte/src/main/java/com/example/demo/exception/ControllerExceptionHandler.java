package com.example.demo.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleException(Exception e) {
		ErrorMessage emsg = new ErrorMessage("E000", e.getMessage());
		return new ResponseEntity<>(emsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ErrorMessage emsg = new ErrorMessage("E004", e.getMessage());
		new ResponseEntity<>(emsg, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(emsg, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException e) {
		ErrorMessage emsg = new ErrorMessage("E005", "errors");
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			emsg.putError(violation.getPropertyPath().toString(),violation.getMessage());
		}
		return new ResponseEntity<>(emsg, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException e) {
		ErrorMessage emsg = new ErrorMessage("E007", e.getMessage());
		return new ResponseEntity<>(emsg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SoldeInsuffisantException.class)
	public ResponseEntity<ErrorMessage> handleUtilisateurNotfoundException(SoldeInsuffisantException e) {
		ErrorMessage emsg = new ErrorMessage("E003", e.getMessage());
		return new ResponseEntity<>(emsg, HttpStatus.BAD_REQUEST);
	}
}