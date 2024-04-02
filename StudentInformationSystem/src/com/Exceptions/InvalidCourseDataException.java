package com.Exceptions;

public class InvalidCourseDataException extends Exception {

	
	private static final long serialVersionUID = -7179301801006120373L;
	String message;

	public InvalidCourseDataException(String message) {
		
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
