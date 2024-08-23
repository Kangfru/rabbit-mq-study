package com.kangfru.sample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {

    @RabbitListener(queues = "hello.queue")
    public void receiveMessage(String msg) {
        log.info("Consume result {}", msg);
    }

}
