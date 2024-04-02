package com.Exceptions;

public class StudentNotFoundException extends Exception {

	
	private static final long serialVersionUID = 4172588411186033063L;
	String message;

	public StudentNotFoundException(String message) {
		
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
