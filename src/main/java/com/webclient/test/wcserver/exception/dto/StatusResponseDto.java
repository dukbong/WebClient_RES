package com.webclient.test.wcserver.exception.dto;

import com.webclient.test.wcserver.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatusResponseDto {
	
	private String status;
	private Object data;
	
    public StatusResponseDto(ErrorCode status) {
        this.status = status.getStatusName();
    }
    
    public static StatusResponseDto success(Object data){
        return new StatusResponseDto(ErrorCode.SUC.getStatusName(), data);
    }
    public static StatusResponseDto serverFail(ErrorCode status, Object data){
        return new StatusResponseDto(status.getStatusName(), data);
    }
}
