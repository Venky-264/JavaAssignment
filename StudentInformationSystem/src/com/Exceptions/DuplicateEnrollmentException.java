package com.Exceptions;

public class DuplicateEnrollmentException extends Exception {
	
	
	private static final long serialVersionUID = 5263489058008379293L;
	String message;

	public DuplicateEnrollmentException(String message) {
	
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
