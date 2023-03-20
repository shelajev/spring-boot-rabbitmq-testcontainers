package com.example.messagingrabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

public class TestMessagingRabbitmqApplication {

  public static void main(String[] args) {
    var application = MessagingRabbitmqApplication.createSpringApplication(args);
    // Here we add the same initializer as we were using in our tests...
    application.addInitializers(new MessagingRabbitmqApplicationTest.Initializer());
    // ... and start it normally
    application.run(args);
  }
}
