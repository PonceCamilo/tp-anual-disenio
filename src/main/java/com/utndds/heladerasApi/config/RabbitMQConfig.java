package com.utndds.heladerasApi.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String HOST = "localhost";
    private static final int PORT = 5672;
    private static final String USERNAME = "grupoDDS";
    private static final String PASSWORD = "grupoDDS";
    public static final String QUEUE_NAME = null;

    @Bean
    public Connection crearConexion() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(HOST);
            factory.setPort(PORT);
            factory.setUsername(USERNAME);
            factory.setPassword(PASSWORD);

            return factory.newConnection();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException("Failed to create RabbitMQ connection", e);
        }
    }
}
