package com.webclient.test.wcserver.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    TEST404_EXCEPTION(HttpStatus.NOT_FOUND, "404 에러 입니다."),
	TEST500_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "500 에러 입니다.");
	
    private final HttpStatus httpStatus;
    private final String detail;
    
}
