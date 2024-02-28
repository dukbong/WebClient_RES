package com.webclient.test.wcserver.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.webclient.test.wcserver.exception.CustomException;
import com.webclient.test.wcserver.exception.StatusCode;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetService {

	public int get() {
		
		log.info("Get Service Call! {}", LocalDateTime.now());
		
		int num = (int)(Math.random() * 10) + 1;
		
		if(num <= 2) {
			throw new CustomException(StatusCode.EX1);
		}
		else if (num <= 4) {
			throw new CustomException(StatusCode.EX2);
		}
		else if (num <= 6) {
			throw new RuntimeException("의문의 Exception 발생1");
		}
		else if (num <= 8) {
			throw new IllegalArgumentException("의문의 Exception 발생2");
		}
		return num;
	}
}
