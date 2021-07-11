package com.yanto.DB.services;

import com.google.gson.Gson;
import com.yanto.DB.model.ResponseMessage;
import com.yanto.DB.model.Order;
import com.yanto.DB.model.User;
import com.yanto.DB.rabbitmq.Send;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Service
public class DBServices {
	
	SqlSession session;
	Send send = new Send();
	
	public void connectMyBatis() throws IOException {
		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		session = sqlSessionFactory.openSession();
	}
	
	public void insertUser(String siswaString) throws IOException, TimeoutException {
		System.out.println("Memulai insert user..");
		User user = new Gson().fromJson(siswaString, User.class);
		connectMyBatis();
		Map<String, Object> data = new HashMap<>();
		data.put("email", user.getEmail());
//		data.put("password", user.getPassword());
		
		session.selectList("Data.viewUser", data);
		int hasil = session.insert("Data.insertUser", user);
		
		session.commit();
		
		if(hasil == 1){
			send.sendToRestApi("Insert user success...");
		} else {
			send.sendToRestApi("Insert user failed...");
		}
	}
	
	public ResponseEntity<String> loginUser(String dataLogin) throws IOException, TimeoutException {
		System.out.println("Waiting for verification..");
		User user = new Gson().fromJson(dataLogin, User.class);
		connectMyBatis();
		Map<String, Object> data = new HashMap<>();
		data.put("email", user.getEmail());
		data.put("password", user.getPassword());
		
		User user1 = session.selectOne("Data.userlog", data);
		session.commit();
		
		if(user1 != null){
			send.sendToRestApi("Login Success!!!");
		} else {
			send.sendToRestApi("Login failed... Email or password is not correct");
		}
		return null;
	}
	
	public void insertOrder(String dataOrder) throws IOException, TimeoutException {
		System.out.println("Memulai insert data..");
		connectMyBatis();
		Order data = new Gson().fromJson(dataOrder, Order.class);
		data.setStatus("Pre Order");
		int hasil = session.insert("Data.insertOrder", data);
		session.commit();
		
		if(hasil == 1){
			send.sendToRestApi("Insert order berhasil");
			//return new ResponseEntity<>("Insert Siswa Berhasil", HttpStatus.OK);
		} else {
			send.sendToRestApi("Insert order gagal...");
		}
	}
	
	public void updateOrder(String dataString) throws IOException, TimeoutException {
		System.out.println("Memulai update data..");
		connectMyBatis();
		
		Order order = new Gson().fromJson(dataString, Order.class);
		order.setStatus("Order Berhasil");
		int hasil = session.update("Data.updateOrder", order);
		session.commit();
		
		if(hasil == 1){
			send.sendToRestApi("Update order berhasil");
			//return new ResponseEntity<>("Insert Siswa Berhasil", HttpStatus.OK);
		} else {
			send.sendToRestApi("Update order gagal...");
		}
	}
}
