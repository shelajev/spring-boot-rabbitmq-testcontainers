/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.messagingrabbitmq;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.lifecycle.Startables;

@SpringBootTest
public class DynamicRabbitMQTest {

  static RabbitMQContainer rabbit = new RabbitMQContainer("rabbitmq:3.7.25-management-alpine"){
    @Override
    public RabbitMQContainer withVhost(String name) {
      return this;
    }
  };

  @DynamicPropertySource
  public static void setupRabbits(DynamicPropertyRegistry registry) {
    Startables.deepStart(rabbit).join();

    registry.add("spring.rabbitmq.host", rabbit::getHost);
    registry.add("spring.rabbitmq.port", rabbit::getAmqpPort);
  }

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private Receiver receiver;

  @Test
  public void test() throws Exception {
    try {
      rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.queueName,
        "Hello from RabbitMQ!");
      receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
    catch (AmqpConnectException e) {
      // ignore - rabbit is not running
    }
  }

}
