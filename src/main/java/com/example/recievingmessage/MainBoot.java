package com.example.recievingmessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainBoot {
  public static void main(String[] args) throws Exception {
   SpringApplication.run(MainBoot.class, args);
   ReceivingMessageApplication application = new ReceivingMessageApplication();
   application.getMessages();
  }

}
