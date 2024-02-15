package com.webclient.test.wcserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webclient.test.wcserver.exception.CustomException;
import com.webclient.test.wcserver.exception.dto.StatusResponseDto;
import com.webclient.test.wcserver.service.GetService;

@RestController
public class WcServerController {

	@Autowired
	GetService getService;
	
	@GetMapping("/")
	public ResponseEntity<StatusResponseDto> getTest() {
		try {
			int num = getService.get();
			return new ResponseEntity<> (StatusResponseDto.success(num), HttpStatus.OK);
		}catch(CustomException e) {
			return new ResponseEntity<>(StatusResponseDto.serverFail(e.getErrorCode().getHttpStatus().value(), e.getErrorCode().getDetail()), e.getErrorCode().getHttpStatus());
		}
	}
}
