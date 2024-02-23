package com.webclient.test.wcserver.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
	private String userId;
	private String requestUrl;
	private String targetSubject;
	private String mainSubject;
}
