package com.Exceptions;

public class InvalidEnrollmentDataException extends Exception{

	private static final long serialVersionUID = 4644265205226210028L;
	String message;

	public InvalidEnrollmentDataException(String message) {
		
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
