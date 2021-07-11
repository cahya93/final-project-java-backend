package com.yanto.rumahmakan.service;

import com.google.gson.Gson;
import com.yanto.rumahmakan.model.Lokasi;
import com.yanto.rumahmakan.model.Payment;
import com.yanto.rumahmakan.model.RumahMakan;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public class RumahMakanServices implements ServiceRM {
	SqlSession session;
	
	public void connectMyBatis() throws IOException {
		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		session = sqlSessionFactory.openSession();
	}
	
	public List<RumahMakan> findAll() throws IOException {
		connectMyBatis();
		List<RumahMakan> data = session.selectList("Data.getAll");

		session.commit();
		session.close();
		
		return data;
	}
	
	public Payment order(String order) throws IOException {
		System.out.println("Memulai insert data..");
		Payment data = new Gson().fromJson(order, Payment.class);
		
		connectMyBatis();
		session.insert("Data.payment", data);
		session.commit();
		session.close();
		System.out.println("Payment success...");
		return data;
	}
	
	@Override
	public List<Lokasi> viewByLokasi(String toJson) throws IOException {
		System.out.println("loading.....");
		Lokasi data = new Gson().fromJson(toJson,Lokasi.class);
		
		connectMyBatis();
		List<Lokasi> data2 = session.selectList("Data.viewByLokasi",data);
		session.commit();
		session.close();
		System.out.println("Get Lokasi sukses...");
		return data2;
	}
}
