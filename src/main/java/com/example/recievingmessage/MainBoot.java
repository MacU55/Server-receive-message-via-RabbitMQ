package com.example.recievingmessage;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class MainBoot {
  public static void main(String[] args) throws Exception {
   SpringApplication.run(MainBoot.class, args);
   ReceivingMessageApplication application = new ReceivingMessageApplication();
   application.getMessages();
  }

}
