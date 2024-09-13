package com.savsoftware.helpdesk.resources.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.savsoftware.helpdesk.services.Exception.DataIntregrityViolationException;
import com.savsoftware.helpdesk.services.Exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectnotfoundexception(ObjectNotFoundException ex, HttpServletRequest request){
		
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Registro não encontrado", ex.getMessage(), request.getRequestURI());
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntregrityViolationException.class)
	public ResponseEntity<StandardError> dataIntregrityViolationException(DataIntregrityViolationException ex, HttpServletRequest request){
		
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Violação de dados", ex.getMessage(), request.getRequestURI());
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> ValidationErros(MethodArgumentNotValidException ex, HttpServletRequest request){
		
		ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Validação.", "Informe os dados abaixo!" , request.getRequestURI());
		
		//List<FieldMessage> errors = new ArrayList<>();
				
		for(FieldError x : ex.getBindingResult().getFieldErrors()) {
						
			errors.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
}
