package com.kangfru.sample.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    SELECT(200, "Select Success");

    private final int status;

    private final String message;

}
