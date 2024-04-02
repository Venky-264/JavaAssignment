package com.Exceptions;

public class TeacherNotFoundException extends Exception {

	
	private static final long serialVersionUID = 876117159680181757L;
	String message;

	public TeacherNotFoundException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
