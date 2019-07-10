package com.klm.backend.klmbackend.exceptions;

public class AirportServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	 
	private Integer code;
	
	public AirportServiceException(Integer code, String msg) {
		super(code +" - " + msg);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	} 
}
