package com.kangfru.sample.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kangfru.sample.model.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(MessageDto messageDto) {
        try {
            // 객체를 JSON으로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            String objectToJSON = objectMapper.writeValueAsString(messageDto);
            rabbitTemplate.convertAndSend("hello.exchange", "hello.key", objectToJSON);
        } catch (JsonProcessingException jpe) {
            System.out.println("파싱 오류 발생");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void directSendMessage(MessageDto messageDto) {
        try {
            // 1. 전송하려는 객체를 문자열로 변환합니다.
            ObjectMapper objectMapper = new ObjectMapper();
            String objectToJSON = objectMapper.writeValueAsString(messageDto);

            // 2. Direct Exchange를 이용하여 라우팅 키(order.pizza)를 기반으로 queue1로 데이터를 전송합니다.
            rabbitTemplate.convertAndSend("exchange.direct", "order.pizza", objectToJSON);
        } catch (JsonProcessingException jpe) {
            System.out.println("파싱 오류 발생");
        }
    }

    /**
     * Fanout 방식을 이용하여 메시지 전송
     *
     * @param messageDto
     */
    @Override
    @Transactional(readOnly = true)
    public void fanoutSendMessage(MessageDto messageDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String objectToJSON = objectMapper.writeValueAsString(messageDto);
            rabbitTemplate.convertAndSend("exchange.fanout", "", objectToJSON);
        } catch (JsonProcessingException jpe) {
            System.out.println("파싱 오류 발생");
        }
    }

    @Override
    public void headerSendMessage(MessageDto messageDto) {
        try {
            // 1. 전송하려는 객체를 문자열로 변환합니다.
            ObjectMapper objectMapper = new ObjectMapper();
            String objectToJSON = objectMapper.writeValueAsString(messageDto);

            // 2. Headers Exchange를 이용하여 queue4로 데이터를 전송합니다.
            rabbitTemplate.convertAndSend("exchange.headers", "", objectToJSON);
        } catch (JsonProcessingException jpe) {
            System.out.println("파싱 오류 발생");
        }
    }

    @Override
    public void topicSendMessage(MessageDto messageDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String objectToJSON = objectMapper.writeValueAsString(messageDto);
            rabbitTemplate.convertAndSend("exchange.topic", "order.*", objectToJSON);
        } catch (JsonProcessingException jpe) {
            System.out.println("파싱 오류 발생");
        }
    }

}
