package com.Exceptions;

public class InvalidStudentDataException extends Exception {

	
	private static final long serialVersionUID = 7447306292429800875L;
	String message;

	public InvalidStudentDataException(String message) {
		
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
