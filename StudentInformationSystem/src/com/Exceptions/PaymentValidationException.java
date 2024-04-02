package com.Exceptions;

public class PaymentValidationException extends Exception{

	private static final long serialVersionUID = -6664828089597508695L;
	String message;

	public PaymentValidationException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
