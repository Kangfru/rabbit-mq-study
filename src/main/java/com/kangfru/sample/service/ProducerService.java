package com.kangfru.sample.service;

import com.kangfru.sample.model.MessageDto;

public interface ProducerService {

    void sendMessage(MessageDto messageDto);

}
