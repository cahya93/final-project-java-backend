package com.yanto.third_party.model;

public class Data {
	
	int id_lokasi;
	String nama_rumah_makan;
	String menu;
	float price;
	
	//Setter Getter
	
	public int getId_lokasi() {
		return id_lokasi;
	}
	
	public void setId_lokasi(int id_lokasi) {
		this.id_lokasi = id_lokasi;
	}
	
	public String getNama_rumah_makan() {
		return nama_rumah_makan;
	}
	
	public void setNama_rumah_makan(String nama_rumah_makan) {
		this.nama_rumah_makan = nama_rumah_makan;
	}
	
	public String getMenu() {
		return menu;
	}
	
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Data{" +
				       "id_lokasi=" + id_lokasi +
				       ", nama_rumah_makan='" + nama_rumah_makan + '\'' +
				       ", menu='" + menu + '\'' +
				       ", price=" + price +
				       '}';
	}
}
