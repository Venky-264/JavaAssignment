package com.Exceptions;

public class NotAssignedCoursesException extends Exception{

	
	private static final long serialVersionUID = -1029450928825395827L;
	String message;

	public NotAssignedCoursesException(String message) {
		
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
