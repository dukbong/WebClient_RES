package com.webclient.test.wcserver.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class TestAdvice2 {

	@ExceptionHandler(value = CustomException.class)
	public void test(CustomException e) {
		log.info("CustomException Adivce");
	}
}
