package com.yanto.rumahmakan.controllers;

import com.google.gson.Gson;
import com.yanto.rumahmakan.model.Lokasi;
import com.yanto.rumahmakan.model.Payment;
import com.yanto.rumahmakan.model.RumahMakan;
import com.yanto.rumahmakan.service.ServiceRM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class EatsController {
	@Autowired
	ServiceRM serviceRM;
	
	//VIEW ALL DATA
	@RequestMapping(value = "/viewall", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<RumahMakan>> listAll() throws IOException {
		List<RumahMakan> data = serviceRM.findAll();
		if (data.isEmpty()) {
			return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	//VIEW BY LOKASI
	@RequestMapping(value = "/viewbylokasi", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<List<Lokasi>> viewbylokasi(@RequestBody Lokasi lokasi) throws IOException {
		
		List<Lokasi> data = serviceRM.viewByLokasi(new Gson().toJson(lokasi));
		if (data.isEmpty()) {
			return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	//ORDER
	@RequestMapping(value = "/order", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> order(@RequestBody Payment payment){
		try {
			serviceRM.order(new Gson().toJson(payment));
		
		} catch (Exception e){
			System.out.println("error = " + e);
		}
		return new ResponseEntity<>(payment, HttpStatus.OK);
	}

}
