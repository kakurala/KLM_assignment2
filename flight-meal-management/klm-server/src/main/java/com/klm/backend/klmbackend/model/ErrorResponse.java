package com.klm.backend.klmbackend.model;

import java.util.Date;

import lombok.Value;

@Value
public class ErrorResponse {

	private Date timestamp;
	private String message;
	private String desc;
	private Integer code;

	public ErrorResponse(Date timestamp, String message, String desc, Integer code) {
		this.timestamp = timestamp;
		this.message = message;
		this.desc = desc;
		this.code = code;
	}

}
