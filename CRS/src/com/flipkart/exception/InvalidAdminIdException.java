package com.flipkart.exception;

public class InvalidAdminIdException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public InvalidAdminIdException(String exception) {
		super();
		this.exception = exception;
	}

	private String exception;

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
}
