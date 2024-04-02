package com.Exceptions;

public class CourseNotFoundException extends Exception {
	
	private static final long serialVersionUID = 4418716421522928814L;
	String message;

	public CourseNotFoundException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		
		return message;
	}
	
}
