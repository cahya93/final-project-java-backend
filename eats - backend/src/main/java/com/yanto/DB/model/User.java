package com.yanto.DB.model;

import org.json.simple.JSONObject;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class User {
//	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id_user;
	private String username;
	private String email;
	private String password;
	private String token;
	private String message;
	
	public User(String email, String password, String token) {
		this.email = email;
		this.password = password;
		this.token = token;
	}
	
	public User() {
	}
	
	public User(String username, String password) {
		this.password = password;
		this.token = token;
	}
	
	public int getId_user() {
		return id_user;
	}
	
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
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
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "User{" +
				       "id_user=" + id_user +
				       ", username='" + username + '\'' +
				       ", email='" + email + '\'' +
				       ", password='" + password + '\'' +
				       ", token='" + token + '\'' +
				       ", message='" + message + '\'' +
				       '}';
	}
}
