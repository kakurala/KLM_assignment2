package com.klm.backend.klmbackend.exceptions;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.klm.backend.klmbackend.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = AirportServiceException.class)
	public ResponseEntity<ErrorResponse> handleException(AirportServiceException exception, WebRequest request) {
		logger.info("In GlobalExceptionHandler, msg = {} ", exception.getMessage());

		ErrorResponse err = new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false), exception.getCode());

		return new ResponseEntity<ErrorResponse>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception exception, WebRequest request) {
		logger.info("In GlobalExceptionHandler, msg = {} ", exception.getMessage());

		ErrorResponse err = new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false), 0);

		return new ResponseEntity<ErrorResponse>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
