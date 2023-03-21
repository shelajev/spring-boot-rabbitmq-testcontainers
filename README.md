# Sample Spring Boot application with RabbitMQ 

This is a sample Spring Boot application showing integration with RabbitMQ and tests with [Testcontainers](https://testcontainers.org). 
The application is the `complete` version of the app one get when following the [guide](https://spring.io/guides/gs/messaging-rabbitmq/). 

# Local development 

You can run the `TestMessagingRabbitmqApplication` class and the application configures its environment and the required dependencies: RabbitMQ, and runs locally.
The RabbitMQ instance is provided in an ephemeral container via Testcontainers, see `MessagingRabbitmqApplicationTest.Initializer`. 

# Running all the tests 
Running Maven test runs all tests and you can import the project into your IDE and run the tests individually. 
```
./mvnw test
```
