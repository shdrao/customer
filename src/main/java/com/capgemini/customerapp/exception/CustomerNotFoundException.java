package com.capgemini.customerapp.exception;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException() {

	}

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
