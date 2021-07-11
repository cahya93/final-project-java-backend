package com.yanto.RestAPIEats.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RestApiReceive {
	private String message;
	public String receiveFromRegistrasi() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare("messageFromDatabase", false, false, false, null);
		System.out.println(" [*] Waiting for messages from database");
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			message = new String(delivery.getBody(), "UTF-8");
			System.out.println(" [x] Received '" + message + "'");
		};
		channel.basicConsume("messageFromDatabase", true, deliverCallback, consumerTag -> {
		});
		return message;
	}
	public String receiveFromDatabase() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare("messageFromDatabase", false, false, false, null);
		System.out.println(" [*] Waiting for messages from database");
	
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			message = new String(delivery.getBody(), "UTF-8");
			System.out.println(" [x] Received '" + message + "'");
		};
		channel.basicConsume("messageFromDatabase", true, deliverCallback, consumerTag -> {
		});
		return message;
	}
	
}
