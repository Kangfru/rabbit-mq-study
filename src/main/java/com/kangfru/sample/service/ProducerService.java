package com.kangfru.sample.service;

import com.kangfru.sample.model.MessageDto;

public interface ProducerService {

    void sendMessage(MessageDto messageDto);

    void directSendMessage(MessageDto messageDto);

    void fanoutSendMessage(MessageDto messageDto);

    void headerSendMessage(MessageDto messageDto);

    void topicSendMessage(MessageDto messageDto);

}
