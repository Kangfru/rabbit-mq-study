package com.kangfru.sample.common.response;

import lombok.*;

@Getter
@ToString
public class ApiResponse {

    private String result;
    private int resultCode;
    private String resultMessage;

    @Builder
    public ApiResponse(String result, int resultCode, String resultMessage) {
        this.result = result;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

}
