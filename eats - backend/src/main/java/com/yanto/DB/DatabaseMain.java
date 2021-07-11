package com.yanto.DB;


//Receive (RabbitMQ) -> MyBatisService -> Database -> Send (RabbitMQ)

import com.yanto.DB.rabbitmq.Receive;

public class DatabaseMain {

    public static Receive receive = new Receive();

    public static void main(String[] args) {
        try{
            System.out.println(" [*] Waiting for messages..");
            receive.insertUser();
            receive.loginUser();
            receive.insertOrder();
            receive.updateOrder();
        }catch (Exception e){
            System.out.println("Error DatabaseMain = " + e);
        }
    }
}
