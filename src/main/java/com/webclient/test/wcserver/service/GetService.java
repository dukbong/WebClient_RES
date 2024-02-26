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
		
		if(num <= 8) {
			throw new CustomException(StatusCode.EX1);
		}
		else if (num <= 9) {
			throw new CustomException(StatusCode.EX2);
		}
		return num;
	}
}
