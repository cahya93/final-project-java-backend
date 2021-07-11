package com.yanto.DB.model;

public class Register {
	String username;
	String email;
	String password;
	String message;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
//	public ResponseMessage(String responseMessage){
//		this.message = responseMessage;
//	}
	
	public String getResponseMessage() {
		return message;
	}
}
