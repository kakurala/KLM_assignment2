package com.klm.api.exceptions;

import org.hibernate.JDBCException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.klm.api.APIMessages;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {
	return buildResponseEntity(new ApiException(HttpStatus.BAD_REQUEST, APIMessages.MALFORMED_REQUEST, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiException apiError) {
	return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    // Business exceptions
    @ExceptionHandler({ FlightNotFoundException.class, FlightExistException.class })
    protected ResponseEntity<Object> flightNotFoundExceptionHandler(Exception ex) {
	ApiException apiError = new ApiException(HttpStatus.NOT_FOUND);
	apiError.setMessage(ex.getMessage());
	return buildResponseEntity(apiError);
    }

    // Unhandled exceptions
    @ExceptionHandler(JDBCException.class)
    protected ResponseEntity<Object> handleEntityNotFound(JDBCException ex) {
	ApiException apiError = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR);
	apiError.setMessage(APIMessages.UNEXCPECTED_DB_ERROR);
	return buildResponseEntity(apiError);
    }
}
