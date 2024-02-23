package com.webclient.test.wcserver.exception.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DBInfo {
	private String time = LocalDateTime.now().toString();
	private String count;
	private String from;
	private String to;
	private String token;
	private String userSn;
	private String url;
	private String ip;
	private String flag;
	private String param;
	private String message;

	public DBInfo count(String count) {
		this.count = count;
		return this;
	}

	public DBInfo from(String from) {
		this.from = from;
		return this;
	}

	public DBInfo to(String to) {
		this.to = to;
		return this;
	}

	public DBInfo token(String token) {
		this.token = token;
		return this;
	}

	public DBInfo userSn(String userSn) {
		this.userSn = userSn;
		return this;
	}

	public DBInfo url(String url) {
		this.url = url;
		return this;
	}

	public DBInfo ip(String ip) {
		this.ip = ip;
		return this;
	}

	public DBInfo flag(String flag) {
		this.flag = flag;
		return this;
	}

	public DBInfo param(String param) {
		this.param = param;
		return this;
	}

	public DBInfo message(String message) {
		this.message = message;
		return this;
	}
}
