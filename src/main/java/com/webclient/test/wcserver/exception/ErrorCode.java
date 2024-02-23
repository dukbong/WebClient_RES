package com.webclient.test.wcserver.exception;

import static org.springframework.http.HttpStatus.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	SUC(OK.value(), "SUCCESS", "SUCCESS MSG"),
    EX1(NOT_FOUND.value(), "ERR01", "message"),
	EX2(INTERNAL_SERVER_ERROR.value(), "ERR02", "500 에러 입니다."),
	
	
	UNKNOWN(700, "UNKNOWN", "알 수 없는 예외 발생 - 서버에 문의 해주시기 바랍니다.");
	
    private final int httpStatus;
    private final String statusName;
    private final String detail;
    
}
