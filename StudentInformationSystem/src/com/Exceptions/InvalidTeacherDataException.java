package com.Exceptions;

public class InvalidTeacherDataException extends Exception {

	private static final long serialVersionUID = -7538327871796373259L;
	String message;

	public InvalidTeacherDataException(String message) {
		
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
