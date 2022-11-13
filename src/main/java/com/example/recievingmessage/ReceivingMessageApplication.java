package com.example.recievingmessage;

import java.nio.charset.StandardCharsets;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReceivingMessageApplication {

  private static Logger log = LoggerFactory.getLogger(ReceivingMessageApplication.class);

  private final static String QUEUE_NAME = "hello";
  private final static long START_TIME = System.currentTimeMillis();
  private final static long WAITE_TIME = 10000L;

  private static DeliverCallback getDeliveryCallBack() {

    return (consumerTag, delivery) -> {
      String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
      System.out.println("Message: " + msg);
    };
  }

  public void getMessages() throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    AMQP.Queue.DeclareOk dok = channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println("Waiting for messages. To exit press CTRL+C");

    if (dok.getMessageCount() == 5) {
       channel.basicConsume(QUEUE_NAME, true, getDeliveryCallBack(), consumerTag -> {
      });
    }

    while ((START_TIME + WAITE_TIME) > System.currentTimeMillis()){
      Thread.sleep(100);
    }

    channel.basicConsume(QUEUE_NAME, true, getDeliveryCallBack(), consumerTag -> {
    });
  }
}
