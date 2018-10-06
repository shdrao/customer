package com.capgemini.customerapp.exception;

public class CustomerExistsException extends RuntimeException {

	public CustomerExistsException() {
	}

	public CustomerExistsException(String message) {
		super(message);

	}

}
