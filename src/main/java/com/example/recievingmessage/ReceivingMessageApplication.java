package com.example.recievingmessage;

import java.nio.charset.StandardCharsets;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ReceivingMessageApplication {

  private final static String QUEUE_NAME = "hello";

  public void getMessages() throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println("Waiting for messages. To exit press CTRL+C");

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
      System.out.println("Received: '" + message + "'");
    };
    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
  }

}