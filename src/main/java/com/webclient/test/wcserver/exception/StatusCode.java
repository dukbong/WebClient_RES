package com.webclient.test.wcserver.exception;

import static org.springframework.http.HttpStatus.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {

	SUC(OK.value(), "SUCCESS", "SUCCESS MSG"),
    EX1(NOT_FOUND.value(), "ERR01", "message"),
	EX2(INTERNAL_SERVER_ERROR.value(), "ERR02", "500 에러 입니다."),
	
	
	// HttpStatus의 공식 코드는 100 ~ 599 이며 이후 코드는 임시적으로 응답을 하기 위한 코드이다.
	COOKIE_EMPTY(698, "COOKIE_EMPTY", "COOKIE 값이 비어있습니다."),
	COOKIE_DNMTC(699, "COOKIE_DNMTC", "필수 COOKIE 값이 누락되어 있습니다."),
	
	
	UNKNOWN(700, "UNKNOWN", "알 수 없는 예외 발생 - 서버에 문의 해주시기 바랍니다.");
	
    private final int httpStatus;
    private final String statusName;
    private final String detail;
    
}
