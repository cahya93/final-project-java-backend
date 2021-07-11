package com.yanto.rumahmakan.model;

public class RumahMakan {
	int id_lokasi;
//	int id_rumah_makan;
//	int id_menu;
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
	
//	public int getId_rumah_makan() {
//		return id_rumah_makan;
//	}
//
//	public void setId_rumah_makan(int id_rumah_makan) {
//		this.id_rumah_makan = id_rumah_makan;
//	}
//
//	public int getId_menu() {
//		return id_menu;
//	}
//
//	public void setId_menu(int id_menu) {
//		this.id_menu = id_menu;
//	}
	
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
	
//	@Override
//	public String toString() {
//		return "rumahMakan{" +
//				       "id_lokasi=" + id_lokasi +
//				       ", id_rumah_makan=" + id_rumah_makan +
//				       ", id_menu=" + id_menu +
//				       ", nama_rumah_makan='" + nama_rumah_makan + '\'' +
//				       ", menu='" + menu + '\'' +
//				       ", price=" + price +
//				       '}';
//	}
}
