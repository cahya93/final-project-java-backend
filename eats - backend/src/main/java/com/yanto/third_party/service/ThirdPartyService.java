package com.yanto.third_party.service;

import com.yanto.third_party.model.Data;
import com.yanto.third_party.model.Lokasi;
import com.yanto.virtualakun.model.Payment;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;

@Service
public class ThirdPartyService {
	private final RestTemplate restTemplate;
	
	public ThirdPartyService(RestTemplateBuilder restTemplateBuilder) {
		
		//Set connection and read timeouts
		this.restTemplate = restTemplateBuilder
				                    .setConnectTimeout(Duration.ofSeconds(500))
				                    .setReadTimeout(Duration.ofSeconds(500))
				                    .build();
	}
	
	public Data[] viewALL() {
		String url = "http://localhost:8010/viewall";
		return this.restTemplate.getForObject(url,
				Data[].class);
	}
	
	public Payment createPostWithObject(Payment payment) {
		String url = "http://localhost:8010/order";
		// send POST request
		return this.restTemplate.postForObject(url,
				payment,
				Payment.class);
	}
	
	public Lokasi[] getDataByLokasi(Lokasi lokasi) {
		String url = "http://localhost:8010/viewbylokasi";
		return this.restTemplate.postForObject(url, lokasi, Lokasi[].class);
	}
	
}
