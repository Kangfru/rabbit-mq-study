package com.kangfru.sample.model;

import com.rabbitmq.client.MessageProperties;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageDto {
    private String title;
    private String message;
    private MessageProperties messageProperties;
    private byte[] messageByte;

    @Builder
    public MessageDto(String title, String message, MessageProperties messageProperties, byte[] messageByte) {
        this.title = title;
        this.message = message;
        this.messageProperties = messageProperties;
        this.messageByte = messageByte;
    }

}
