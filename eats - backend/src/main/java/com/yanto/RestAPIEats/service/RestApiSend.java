package com.yanto.RestAPIEats.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class RestApiSend {

    public void insertUser(String dataString) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("insertUser", false, false, false, null);
            //String message = "Assalamualaikum";
            channel.basicPublish("", "insertUser", null, dataString.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + dataString + "'");
        }
    }
    
    public void loginUser(String dataLogin) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("loginUser", false, false, false, null);
            //String message = "Assalamualaikum";
            channel.basicPublish("", "loginUser", null, dataLogin.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + dataLogin + "'");
        }
    }
    
    public void insertOrder(String dataString) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("insertOrder", false, false, false, null);
            //String message = "Assalamualaikum";
            channel.basicPublish("", "insertOrder", null, dataString.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + dataString + "'");
        }
    }
    
    public void updateOrder(String dataString) throws IOException, TimeoutException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("updateOrder", false, false, false, null);
            //String message = "Assalamualaikum";
            channel.basicPublish("", "updateOrder", null, dataString.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + dataString + "'");
        }
    }
}
