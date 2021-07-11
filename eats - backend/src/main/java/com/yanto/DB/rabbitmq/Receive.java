package com.yanto.DB.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.yanto.DB.services.DBServices;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Receive {

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private DBServices dbServices = new DBServices();
//    private Adapter adapter = new Adapter();


    public void connectRabbitMQ() throws IOException, TimeoutException {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
    }

    public void insertUser() {
        try{
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("insertUser", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String dataString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + dataString + "'");
                try {
                    dbServices.insertUser(dataString);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("insertUser", true, deliverCallback, consumerTag -> { });
        }catch (Exception e) {
            System.out.println("Error insertUser = " + e);
        }
    }
    
    public void loginUser() {
        try{
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("loginUser", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String dataString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + dataString + "'");
                try {
                    dbServices.loginUser(dataString);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("loginUser", true, deliverCallback, consumerTag -> { });
        }catch (Exception e) {
            System.out.println("Error loginUser = " + e);
        }
    }

   public void insertOrder() {
        try{
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("insertOrder", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String dataString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + dataString + "'");
                try {
                    dbServices.insertOrder(dataString);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("insertOrder", true, deliverCallback, consumerTag -> { });
        }catch (Exception e) {
            System.out.println("Error insertOrder = " + e);
        }
    }
    
    public void updateOrder() {
        try{
            connectRabbitMQ();
            channel = connection.createChannel();
            channel.queueDeclare("updateOrder", false, false, false, null);
            //System.out.println(" [*] Waiting for messages..");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String dataString = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + dataString + "'");
                try {
                    dbServices.updateOrder(dataString);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            };
            channel.basicConsume("updateOrder", true, deliverCallback, consumerTag -> { });
        }catch (Exception e) {
            System.out.println("Error UpdateOrder = " + e);
        }
    }
}
