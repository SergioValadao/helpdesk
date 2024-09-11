package com.savsoftware.helpdesk.services.Exception;

public class DataIntregrityViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntregrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntregrityViolationException(String message) {
		super(message);
	}
	
	

}
