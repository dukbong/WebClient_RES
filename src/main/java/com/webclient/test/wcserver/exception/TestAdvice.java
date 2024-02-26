package com.webclient.test.wcserver.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class TestAdvice {

	@ExceptionHandler({Exception.class, RuntimeException.class})
	public void test(Exception e) {
		log.info("여기는 원래 있는 Exception Adivce");
		log.info("넘어온 Exception INFO = {}", e.toString());
		log.info("넘어온 Exception MSG  = {}", e.getMessage());
		log.info("기존 Exception Handler로 기존 DB에서 저장 처리 완료~");
	}
}
