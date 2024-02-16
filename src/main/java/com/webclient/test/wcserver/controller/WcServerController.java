package com.webclient.test.wcserver.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webclient.test.wcserver.exception.CustomException;
import com.webclient.test.wcserver.exception.dto.StatusResponseDto;
import com.webclient.test.wcserver.exception.dto.TestVo;
import com.webclient.test.wcserver.service.GetService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class WcServerController {

	@Autowired
	GetService getService;

	@PostMapping("/")
	public ResponseEntity<StatusResponseDto> getTest(@RequestBody Object bodyData) {
		try {
			log.info("요청으로부터 받은 값 ==================");
			log.info("bodyData Name   : {}", bodyData.getClass().getName());
			log.info("body            : {}", bodyData);
			log.info("=================================");
			getService.get(); // 서비스 로직
			TestVo testVo1 = new TestVo("test1_name", "test1_age");
			TestVo testVo2 = new TestVo("test2_name", "test2_age");
			TestVo testVo3 = new TestVo("test3_name", "test3_age");
			List<TestVo> test = new ArrayList<>();
			test.add(testVo1);
			test.add(testVo2);
			test.add(testVo3);
			return new ResponseEntity<>(StatusResponseDto.success(test), HttpStatus.OK); // 정상 응답
		} catch (CustomException e) {
			return new ResponseEntity<>(StatusResponseDto.serverFail(e.getErrorCode().getHttpStatus().value(),
					e.getErrorCode().getDetail()), e.getErrorCode().getHttpStatus()); // 요청 받는 측에서 터진 에러
		}
	}
	
	@PostMapping("/1")
	public ResponseEntity<StatusResponseDto> getTest2(@RequestBody Object bodyData){
		log.info("요청으로부터 받은 값 ==================");
		log.info("bodyData Fields : {}", Arrays.toString(bodyData.getClass().getFields()));
		log.info("bodyData Name   : {}", bodyData.getClass().getName());
		log.info("body            : {}", bodyData);
		log.info("=================================");
		try {
			return ResponseEntity.ok().body(StatusResponseDto.success(99999));
		}catch(CustomException e) {
			return new ResponseEntity<>(StatusResponseDto.serverFail(e.getErrorCode().getHttpStatus().value(), e.getErrorCode().getDetail()), e.getErrorCode().getHttpStatus());
		}
	}
}
