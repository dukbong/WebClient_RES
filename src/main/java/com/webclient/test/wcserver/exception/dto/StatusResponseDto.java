package com.webclient.test.wcserver.exception.dto;

import com.webclient.test.wcserver.exception.StatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatusResponseDto {
	
	private String status;
	private Object data;
	
    public StatusResponseDto(StatusCode status) {
        this.status = status.getStatusName();
    }
    
    public static StatusResponseDto success(Object data){
        return new StatusResponseDto(StatusCode.SUC.getStatusName(), data);
    }
    public static StatusResponseDto serverFail(StatusCode status, Object data){
        return new StatusResponseDto(status.getStatusName(), data);
    }
}
