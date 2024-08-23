package com.kangfru.sample.model;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageDto {

    private String tile;

    private String message;

    @Builder
    public MessageDto(String tile, String message) {
        this.tile = tile;
        this.message = message;
    }

}
