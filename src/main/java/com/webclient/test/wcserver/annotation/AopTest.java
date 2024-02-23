package com.webclient.test.wcserver.annotation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webclient.test.wcserver.exception.CustomException;
import com.webclient.test.wcserver.exception.ErrorCode;
import com.webclient.test.wcserver.exception.dto.DBInfo;
import com.webclient.test.wcserver.exception.dto.StatusResponseDto;

import lombok.extern.slf4j.Slf4j;

/***
 * 응답 서버에서 Controller에 @ApiSubjectResponse 붙이면 자동으로 결과를 DB에 Insert 및 클라이언트에게 return처리
 */
@Aspect
@Component
@Slf4j
public class AopTest {
	
	private ObjectMapper objectMapper = new ObjectMapper();
    
    @Around("@annotation(ApiSubjectResponse)")
    public Object testAop(ProceedingJoinPoint joinPoint) throws Throwable {
    	DBInfo dbInfo = new DBInfo();
        try {
        	StringBuffer sb = new StringBuffer();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Cookie[] cookies = request.getCookies();
            if(cookies.length != 5) throw new Exception("Cookie 값을 확인 해주시기 바랍니다.");
            // Cookie 값
            for(Cookie cookie : cookies) {
				if(cookie.getName().equals("userSn")) dbInfo.userSn(cookie.getValue());
				if(cookie.getName().equals("url")) dbInfo.url(cookie.getValue());
				if(cookie.getName().equals("from")) dbInfo.from(cookie.getValue());
				if(cookie.getName().equals("to")) dbInfo.to(cookie.getValue());
				if(cookie.getName().equals("count")) dbInfo.count(cookie.getValue());
            }
            // 매개변수 (body)
            Object[] args = joinPoint.getArgs();
            for(int i = 0; i < args.length - 1; i++) {
            	Object arg = args[i];
            	sb.append(objectConvertJson(arg)).append(" ");
            }
            dbInfo.ip(request.getRemoteAddr()).param(sb.toString());
            
            //-----------------------------------------------------------------------------------------공통 부분
            Object result = joinPoint.proceed();
            
            if(result instanceof ResponseEntity<?>) {
            	ResponseEntity<?> re = (ResponseEntity<?>)result;
            	Object reBody = re.getBody();
            	if(reBody instanceof StatusResponseDto) {
            		StatusResponseDto statusResponseDto = (StatusResponseDto) reBody;
            		dbInfo.message(objectConvertJson(statusResponseDto.getData())).flag("N");
            		log.info("Response Server DB Insert [SUCCESS ⭕] ==> 요청시간 : {}, 요청 횟수 : {}, From : {}, To : {}, 요청 ID : {}, 요청 URL : {}, 요청 IP : {}, 구분 : {}, Param : {}, 응답 메시지 : {}",
//					LocalDate.now(), rd.getMainSubject(), rd.getTargetSubject(), rd.getUserId(), rd.getRequestUrl(), "IP 아직 없음", "N", objectConvertJson(bodyData), objectConvertJson(test));
            				dbInfo.getTime(), dbInfo.getCount(), dbInfo.getFrom(), dbInfo.getTo(), dbInfo.getUserSn(), dbInfo.getUrl(), dbInfo.getIp(), dbInfo.getFlag(), dbInfo.getParam(), dbInfo.getMessage());
            		return new ResponseEntity<>(statusResponseDto, re.getStatusCode()); // 정상 출력
            	}else {
            		throw new Exception("반환타입의 제네릭 타입이 잘못지정 되었습니다.");
            	}
            }
            throw new Exception("Controller의 반환 타입이 잘못되었습니다.");
        } catch (Exception e) {
        	
        	dbInfo.message(objectConvertJson(e.getStackTrace())).flag("Y");
        	log.info("Response Server DB Insert [FAILD ❌] ==> 요청시간 : {}, 요청 횟수 : {} From : {}, To : {}, 요청 ID : {}, 요청 URL : {}, 요청 IP : {}, 구분 : {}, Param : {}, 응답 메시지 : {}",
        			dbInfo.getTime(), dbInfo.getCount(), dbInfo.getFrom(), dbInfo.getTo(), dbInfo.getUserSn(), dbInfo.getUrl(), dbInfo.getIp(), dbInfo.getFlag(), dbInfo.getParam(), dbInfo.getMessage());	
        	
        	if(e instanceof CustomException) {
        		// 실제 Method 내부에서 CustomException이 터진 경우
        		ErrorCode errorCode = ((CustomException) e).getErrorCode();
        		return new ResponseEntity<>(StatusResponseDto.serverFail(errorCode, errorCode.getDetail()), HttpStatus.valueOf(errorCode.getHttpStatus()));
        	} else {
        		// 지정되어 있지 않은 오류 관련하여 반환
        		return new ResponseEntity<>(StatusResponseDto.serverFail(ErrorCode.UNKNOWN, e.getMessage().isEmpty() ? ErrorCode.UNKNOWN.getDetail() : e.getMessage()), HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
        	}
        }
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
