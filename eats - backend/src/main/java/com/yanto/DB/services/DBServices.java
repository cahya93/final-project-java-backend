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
import java.util.regex.Pattern;

@Service
public class DBServices {
	
	SqlSession session;
	Send send = new Send();
	
	public void connectMyBatis() throws IOException {
		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		session = sqlSessionFactory.openSession();
	}
	public static boolean isValidEmail(String email){// regex validation for email
		// Regex to check valid email.
		String regex = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";
		// Compile the ReGex
		boolean b = Pattern.matches(regex, email);
		if (email == null) {
			return false;
		}
		return b;
	}
	public static boolean isValidPassword(String password){//regex Validation for password
		// Regex to check valid password.
		String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_])(?=\\S+$).{8,}";
		// Compile the ReGex
		boolean b1 = Pattern.matches(regex, password);
		if (password == null) {
			return false;
		}
		return b1;
	}
	public boolean Login(User user){//Login Method
		boolean hasil;
		boolean e = isValidEmail(user.getUsername());//get boolean regex for valid email
		boolean p = isValidPassword(user.getPassword());//get boolean regex for valid password
		System.out.println("Validation Process");
		if(e  &&  p){//Validate Email and Password Format Using Regex
			return hasil = true;
		}else{
			return hasil = false;
		}
		
		
	}
	
	public void insertUser(String siswaString) throws IOException, TimeoutException {
		System.out.println("Memulai insert user..");
		User user = new Gson().fromJson(siswaString, User.class);
		connectMyBatis();
		Map<String, Object> data = new HashMap<>();
		data.put("email", user.getEmail());
		data.put("password", user.getPassword());
		
		session.selectList("Data.viewUser");
		
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
	public void resetPass(User user) throws IOException {
		connectMyBatis();
		String username = user.getUsername();
		String password = user.getPassword();
		
		User user1 = new User(username,password);
		session.insert("Data.resetPass",user1);
		session.commit();
		session.close();
		System.out.println("Password reset Successfully");
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
