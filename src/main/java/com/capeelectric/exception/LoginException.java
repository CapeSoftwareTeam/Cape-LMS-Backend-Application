package com.capeelectric.exception;

public class LoginException {
	private static final long serialVersionUID = 1L;
	
	private String message;

	
	public LoginException() {}
	public LoginException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	
}
