package com.kangfru.firstrabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    private static final String ROUTING_KEY = "foo.bar.baz";
    private static final String MESSAGE_CONTENT = "Hello from the other side.";
    private static final long AWAIT_DURATION = 10000L;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws InterruptedException {
        sendMessage();
        awaitResponse();
    }

    private void sendMessage() {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, ROUTING_KEY, MESSAGE_CONTENT);
    }

    private void awaitResponse() throws InterruptedException {
        receiver.getLatch().await(AWAIT_DURATION, TimeUnit.MILLISECONDS);
    }
}
