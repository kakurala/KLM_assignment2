package com.klm.backend.klmbackend.exceptions;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.klm.backend.klmbackend.model.ApiException;
import com.klm.backend.klmbackend.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = AirportServiceException.class)
	public ResponseEntity<ApiException> handleException(AirportServiceException exception, WebRequest request) {
		logger.info("In GlobalExceptionHandler, msg = {} ", exception.getMessage());

//		ApiException err = new ApiException(new Date(), exception.getMessage(), request.getDescription(false), exception.getCode());

		ApiException apiError = new ApiException(HttpStatus.NOT_FOUND);
		apiError.setMessage(exception.getMessage());
		
		return new ResponseEntity<ApiException>(apiError, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiException> handleGenericException(Exception exception, WebRequest request) {
		logger.info("In GlobalExceptionHandler, msg = {} ", exception.getMessage());
 
		ApiException apiError = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage(exception.getMessage());
		
		return new ResponseEntity<ApiException>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
