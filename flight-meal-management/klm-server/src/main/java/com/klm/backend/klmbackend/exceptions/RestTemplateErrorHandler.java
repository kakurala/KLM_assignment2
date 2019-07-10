package com.klm.backend.klmbackend.exceptions;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.klm.backend.klmbackend.service.AirportServiceConstants;


@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler{

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		
		boolean is4xxSeries = response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR;
		boolean is5xxSeries = response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
		
		// if API returns any of 4XX or 5xx http code then consider the call is failed
		return is4xxSeries || is5xxSeries;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
	
		boolean is4xxSeries = response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR;
		boolean is5xxSeries = response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;

		System.out.println(response.getBody());
		
		// Handle 4xx and 5xx codes separately
		if(is4xxSeries){
			throw new AirportServiceException(AirportServiceConstants.NO_DATA_FOUND_E1001, AirportServiceConstants.NO_DATA_FOUND_MSG);
		}else if(is5xxSeries){
			throw new AirportServiceException(AirportServiceConstants.GENERIC_ERROR_E1003, AirportServiceConstants.SERVER_ERR_MSG);
		}
	}

}
