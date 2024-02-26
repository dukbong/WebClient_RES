package com.webclient.test.wcserver.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webclient.test.wcserver.annotation.ApiSubjectResponse;
import com.webclient.test.wcserver.exception.dto.StatusResponseDto;
import com.webclient.test.wcserver.exception.dto.TestVo;
import com.webclient.test.wcserver.service.GetService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class WcServerController {
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	GetService getService;

	@ApiSubjectResponse
	@PostMapping("/")
	public ResponseEntity<StatusResponseDto> getTest(@RequestBody Object bodyData, HttpServletRequest request) throws Exception {
//		DBInfo dbInfo = new DBInfo();
//		Exception resException = null;
//		try {
			log.info("요청으로부터 받은 값 ==================");
			log.info("IP address      : {}", request.getRemoteAddr()); // 요청한 곳의 IP
			log.info("bodyData Name   : {}", bodyData.getClass().getName());
			log.info("body            : {}", bodyData);
//			dbInfo.param(objectConvertJson(bodyData)).ip(request.getRemoteAddr());
//			Cookie[] cookies = request.getCookies();
//			for(Cookie cookie : cookies) {
//				log.info("{} : {}", cookie.getName(), cookie.getValue());
//				if(cookie.getName().equals("userId")) dbInfo.userSn(cookie.getValue());
//				if(cookie.getName().equals("requestUrl")) dbInfo.url(cookie.getValue());
//				if(cookie.getName().equals("mainSubject")) dbInfo.from(cookie.getValue());
//				if(cookie.getName().equals("targetSubject")) dbInfo.to(cookie.getValue());
//			}
			log.info("=================================");
			
//			RequestDto dt = (RequestDto) bodyData;
//			dt.getUserId();
			
			getService.get(); // 서비스 로직
			TestVo testVo1 = new TestVo("test1_name", "test1_age");
			TestVo testVo2 = new TestVo("test2_name", "test2_age");
			TestVo testVo3 = new TestVo("test3_name", "test3_age");
			List<TestVo> test = new ArrayList<>();
			test.add(testVo1);
			test.add(testVo2);
			test.add(testVo3);
			
//			dbInfo.flag("N").message(objectConvertJson(test));
//			log.info("Response Server DB Insert [SUCCESS ⭕] ==> 요청시간 : {}, From : {}, To : {}, 요청 ID : {}, 요청 URL : {}, 요청 IP : {}, 구분 : {}, Param : {}, 응답 메시지 : {}",
//			LocalDate.now(), rd.getMainSubject(), rd.getTargetSubject(), rd.getUserId(), rd.getRequestUrl(), "IP 아직 없음", "N", objectConvertJson(bodyData), objectConvertJson(test));
//			dbInfo.getTime(), dbInfo.getFrom(), dbInfo.getTo(), dbInfo.getUserSn(), dbInfo.getUrl(), dbInfo.getIp(), dbInfo.getFlag(), dbInfo.getParam(), dbInfo.getMessage());
//			return test;
			return ResponseEntity.ok().body(StatusResponseDto.success(test));
//			return new ResponseEntity<>(StatusResponseDto.success(test), HttpStatus.OK); // 정상 응답
//		} catch (CustomException e) {
//			resException = e;
//			log.info("Response Server DB Insert [FAILD ❌] ==> 요청시간 : {}, From : {}, To : {}, 요청 ID : {}, 요청 URL : {}, 요청 IP : {}, 구분 : {}, Param : {}, 응답 메시지 : {}",
//			LocalDate.now(), rd.getMainSubject(), rd.getTargetSubject(), rd.getUserId(), rd.getRequestUrl(), "IP 아직 없음", "Y", objectConvertJson(bodyData), objectConvertJson(e.getStackTrace()));
//			throw e;
//		} catch(Exception e) {
//			
//		} finally {
//			if(resException != null) {
//				ErrorCode errCode = ((CustomException) resException).getErrorCode();
//				return new ResponseEntity<>(StatusResponseDto.serverFail(errCode.getHttpStatus().value(),
//						errCode.getDetail()), errCode.getHttpStatus()); // 요청 받는 측에서 터진 에러
//			}
//		}
	}
	
//	@PostMapping("/1")
//	public ResponseEntity<StatusResponseDto> getTest2(@RequestBody Object bodyData){
//		log.info("요청으로부터 받은 값 ==================");
//		log.info("bodyData Fields : {}", Arrays.toString(bodyData.getClass().getFields()));
//		log.info("bodyData Name   : {}", bodyData.getClass().getName());
//		log.info("body            : {}", bodyData);
//		log.info("=================================");
//		try {
//			return ResponseEntity.ok().body(StatusResponseDto.success(99999));
//		}catch(CustomException e) {
//			return new ResponseEntity<>(StatusResponseDto.serverFail(e.getErrorCode().getHttpStatus().value(), e.getErrorCode().getDetail()), e.getErrorCode().getHttpStatus());
//		}
//	}
//	
	@PostMapping("/2")
	public ResponseEntity<StatusResponseDto> post3(@RequestBody Object bodyData){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(StatusResponseDto.success("5초 sleep"));
	}
	
	
	public String objectConvertJson(Object obj){
		try {
			return objectMapper.writeValueAsString(obj);
		}catch(JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
