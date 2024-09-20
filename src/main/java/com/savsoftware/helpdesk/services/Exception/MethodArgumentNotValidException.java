package com.savsoftware.helpdesk.services.Exception;

public class MethodArgumentNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MethodArgumentNotValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public MethodArgumentNotValidException(String message) {
		super(message);
	}	
}
