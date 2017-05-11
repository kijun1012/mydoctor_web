package com.mydoctor.exception;


public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4451685904043827749L;
	
	private String username;
	
	public UserNotFoundException(String username) {
		this.username = username;
	}
	
	public String getUserName() {
		return username;
	}
}
