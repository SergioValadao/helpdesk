package com.savsoftware.helpdesk.resources.Excption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.savsoftware.helpdesk.services.Exception.DataIntregrityViolationException;
import com.savsoftware.helpdesk.services.Exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandarError> objectnotfoundexception(ObjectNotFoundException ex, HttpServletRequest request){
		
		StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Registro não encontrado", ex.getMessage(), request.getRequestURI());
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntregrityViolationException.class)
	public ResponseEntity<StandarError> dataIntregrityViolationException(DataIntregrityViolationException ex, HttpServletRequest request){
		
		StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Violação de dados", ex.getMessage(), request.getRequestURI());
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
