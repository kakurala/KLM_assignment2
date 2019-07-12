package com.klm.api.exceptions;

import com.klm.api.model.ApiResponseMessage;

public class FlightNotFoundException extends Exception {

    private static final long serialVersionUID = 8830962879468173058L;
    
    private int code;
    private String type;
    private String message;

    public FlightNotFoundException(ApiResponseMessage apiResponseMessage) {
	super(apiResponseMessage.getMessage());
	this.code = apiResponseMessage.getCode();
	this.type = apiResponseMessage.getType();
	this.message = apiResponseMessage.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
