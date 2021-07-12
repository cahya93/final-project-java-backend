package com.yanto.RestAPIEats.controllers;

import com.google.gson.Gson;
import com.yanto.DB.model.Register;
import com.yanto.DB.model.User;
import com.yanto.DB.services.DBServices;
import com.yanto.RestAPIEats.service.RestApiReceive;
import com.yanto.RestAPIEats.service.RestApiSend;
import com.yanto.RestAPIEats.util.CustomErrorType;
import com.yanto.RestAPIEats.util.MessageSuccess;
import com.yanto.third_party.service.ThirdPartyService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@RestController
public class UserController {
	RestApiSend restApiSend = new RestApiSend();
	RestApiReceive restApiReceive = new RestApiReceive();
	@Autowired
	ThirdPartyService thirdPartyService;

	@PostMapping("user/registrasi")
	public String registrasi(@RequestParam("username") String username,
	                         @RequestParam("password") String password,
	                         @RequestParam("email") String email) throws IOException, TimeoutException {
		
//		String token = getJWTToken(username);
		Register user = new Register();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setMessage("Registrasi " + username +" Success");
		
		String data = user.getResponseMessage();
		
		try {
			restApiSend.insertUser(new Gson().toJson(user));
//			restApiReceive.receiveFromDatabase();
		}catch (Exception e){
			System.out.println("error = " + e);
		}
		delay();
		return restApiReceive.receiveFromRegistrasi();
	}
	
	@PostMapping("user/login")
	public String login(@RequestParam("email") String email,
	                               @RequestParam("password") String password) throws IOException, TimeoutException {
		String token = getJWTToken(email);
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setToken(token);
		
		User data = new User(email, password, token);
		data.getEmail();
		data.getPassword();
		data.getToken();
		
		try {
			restApiSend.loginUser(new Gson().toJson(data));
//			restApiReceive.receiveFromDatabase();
		} catch (Exception e){
			System.out.println("Error = " + e);
		}
		delay();
		return restApiReceive.receiveFromDatabase();
	}
	
	@RequestMapping(value = "/forgetPass", method = RequestMethod.POST)
	public ResponseEntity<?> ForgetPassword(@RequestBody User user) throws Exception {
		try {
			restApiSend.UserData(new Gson().toJson(user),"ForgetPass");
			String token = getJWTToken(user.getEmail());
			user.setToken(token);
			String data = restApiReceive.AllRecieve("MessagePass");
			if(data.equalsIgnoreCase("Success")){
				return new ResponseEntity<>(new MessageSuccess("Please Use this Token: " + user.getToken()), HttpStatus.CREATED);
			}else{
				return new ResponseEntity<>(new CustomErrorType("Please SignUp First"), HttpStatus.NOT_FOUND);
			}
		}catch (Exception e){
			System.out.println("error = " + e + " Database Not yet Starting");
			return new ResponseEntity<>(new CustomErrorType("Database Not yet Started"),HttpStatus.CREATED);
		}
	}
	@RequestMapping(value = "/Resetpass", method = RequestMethod.PUT)
	public ResponseEntity<?> ResetPass(@RequestBody User user1) throws Exception {
		DBServices repo = new DBServices();
		try {
			if(repo.Login(user1)==true){
				restApiSend.UserData(new Gson().toJson(user1),"ResetPass");
			}else {
				return new ResponseEntity<>(new CustomErrorType("Wrong Password Format"),HttpStatus.CREATED);
			}
			
		}catch (Exception e){
			System.out.println("error = " + e);
			return new ResponseEntity<>(new CustomErrorType("Password or Username Cannot Be empty"),HttpStatus.CREATED);
		}
		String hasil = restApiReceive.AllRecieve("Reseted");
		System.out.println(hasil);
		if(hasil.equalsIgnoreCase("Failed")){
			return new ResponseEntity<>(new CustomErrorType("Password Failed to reset"),HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(new CustomErrorType("Password has been reset"), HttpStatus.NOT_FOUND);
		}
	}
	
	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Token " + token;
	}
	private static void delay() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException _ignored) {
			Thread.currentThread().interrupt();
		}
	}
}
