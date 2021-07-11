package com.yanto.rumahmakan.service;

import com.yanto.rumahmakan.model.Lokasi;
import com.yanto.rumahmakan.model.Payment;
import com.yanto.rumahmakan.model.RumahMakan;

import java.io.IOException;
import java.util.List;

public interface ServiceRM {
	List<RumahMakan> findAll() throws IOException;
	
	Payment order(String toJson) throws IOException;
	
	List<Lokasi> viewByLokasi(String lokasi) throws IOException;
}
