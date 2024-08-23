package com.kangfru.sample.controller;

import com.kangfru.sample.common.code.SuccessCode;
import com.kangfru.sample.common.response.ApiResponse;
import com.kangfru.sample.model.MessageDto;
import com.kangfru.sample.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/producer")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    /**
     * 생산자(Proceduer)가 메시지를 전송합니다.
     *
     * @param messageDto
     * @return
     */
    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto) {
        String result = "";

        producerService.sendMessage(messageDto);

        ApiResponse ar = ApiResponse.builder()
                .result(result)
                .resultCode(SuccessCode.SELECT.getStatus())
                .resultMessage(SuccessCode.SELECT.getMessage())
                .build();
        return new ResponseEntity<>(ar, HttpStatus.OK);
    }

    @PostMapping("/direct-message")
    public ResponseEntity<?> sendDirectMessage(@RequestBody MessageDto messageDto) {
        String result = "";

        producerService.directSendMessage(messageDto);

        ApiResponse ar = ApiResponse.builder()
                .result(result)
                .resultCode(SuccessCode.SELECT.getStatus())
                .resultMessage(SuccessCode.SELECT.getMessage())
                .build();
        return new ResponseEntity<>(ar, HttpStatus.OK);
    }

    @PostMapping("/fanout-message")
    public ResponseEntity<?> sendFanoutMessage(@RequestBody MessageDto messageDto) {
        producerService.fanoutSendMessage(messageDto);
        ApiResponse ar = ApiResponse.builder()
                .result("")
                .resultCode(SuccessCode.SELECT.getStatus())
                .resultMessage(SuccessCode.SELECT.getMessage())
                .build();
        return new ResponseEntity<>(ar, HttpStatus.OK);
    }

    @PostMapping("/header-message")
    public ResponseEntity<?> sendHeaderMessage(@RequestBody MessageDto messageDto) {
        producerService.headerSendMessage(messageDto);
        ApiResponse ar = ApiResponse.builder()
                .result("")
                .resultCode(SuccessCode.SELECT.getStatus())
                .resultMessage(SuccessCode.SELECT.getMessage())
                .build();
        return new ResponseEntity<>(ar, HttpStatus.OK);
    }

    @PostMapping("/topic-message")
    public ResponseEntity<?> sendTopicMessage(@RequestBody MessageDto messageDto) {
        producerService.topicSendMessage(messageDto);
        ApiResponse ar = ApiResponse.builder()
                .result("")
                .resultCode(SuccessCode.SELECT.getStatus())
                .resultMessage(SuccessCode.SELECT.getMessage())
                .build();
        return new ResponseEntity<>(ar, HttpStatus.OK);
    }

}
