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
	public String messages;
	public String AllRecieve(String Queue) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		final Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();
		
		channel.queueDeclare(Queue, true, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		channel.basicQos(1);
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.println(" [x] Received '" + message + "'");
			try {
				if(message.equals("Error")){
					messages = "Failed";
				}else{
					messages = "success";
				}
			} finally {
				System.out.println("[x] Done");
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			}
		};
		channel.basicConsume(Queue, false, deliverCallback, consumerTag -> { });
		delay();
		return messages;
	}
	private static void delay() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException _ignored) {
			Thread.currentThread().interrupt();
		}
	}
}
