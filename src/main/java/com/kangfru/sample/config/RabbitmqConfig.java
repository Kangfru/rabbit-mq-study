package com.kangfru.sample.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Bean
    Queue queue() {
        return new Queue("hello.queue", false);
    }

    @Bean
    Binding binding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with("hello.key");
    }

    /**
     * Direct Exchange Binding
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("exchange.direct");
    }

    @Bean
    Queue queue1() {
        return new Queue("queue1", false);
    }

    @Bean
    Binding directBinding() {
        return BindingBuilder.bind(queue1()).to(directExchange()).with("order.pizza");
    }

    /**
     * Fanout Exchange 수행
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("exchange.fanout");
    }

    @Bean
    Queue queue2() {
        return new Queue("queue2", false);
    }

    @Bean
    Queue queue3() {
        return new Queue("queue3", false);
    }

    @Bean
    Binding fanoutBinding1(FanoutExchange fanoutExchange, Queue queue2) {
        return BindingBuilder.bind(queue2).to(fanoutExchange);
    }

    @Bean
    Binding fanoutBinding2(FanoutExchange fanoutExchange, Queue queue3) {
        return BindingBuilder.bind(queue3).to(fanoutExchange);
    }

    /**
     * Header Exchange
     */
    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange("exchange.headers");
    }

    @Bean
    Queue queue4() {
        return new Queue("queue4", false);
    }

    @Bean
    Binding headersBinding(HeadersExchange headersExchange, Queue queue4) {
        return BindingBuilder
                .bind(queue4)
                .to(headersExchange)
                .where("x-api-key")     // Header 내에 "x-api-key" 라는 값이 존재하는 경우
                .exists();
    }

    /**
     * Topic Exchange 수행
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("exchange.topic");
    }

    @Bean
    Queue queue5() {
        return new Queue("queue5", false);
    }

    @Bean
    Binding topicBinding(TopicExchange topicExchange, Queue queue5) {
        return BindingBuilder
                .bind(queue5)
                .to(topicExchange)
                .with("order.*");
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

}
