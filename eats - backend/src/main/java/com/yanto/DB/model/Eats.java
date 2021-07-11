package com.yanto.DB.model;

public class Eats {
	int id_user;
	String username;
	String email;
	String password;
	
	int id_lokasi;
	String name_lokasi;
	
	public Eats(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public Eats() {
	
	}
	
	//setter and getter
	
	
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
	
	public int getId_lokasi() {
		return id_lokasi;
	}
	
	public void setId_lokasi(int id_lokasi) {
		this.id_lokasi = id_lokasi;
	}
	
	public String getName_lokasi() {
		return name_lokasi;
	}
	
	public void setName_lokasi(String name_lokasi) {
		this.name_lokasi = name_lokasi;
	}
}
