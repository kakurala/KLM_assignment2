package com.klm.backend.klmbackend.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.klm.backend.klmbackend.exceptions.AirportServiceException;
import com.klm.backend.klmbackend.model.ApiResponseMessage;
import com.klm.backend.klmbackend.model.Flight;
import com.klm.backend.klmbackend.model.Meals;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${flights.api.addFlights.endpoint}")
    private String addFlightsEndpoint;

    @Value("${flights.api.addMeals.endpoint}")
    private String addMealsEndpoint;

    @Value("${flights.api.deleteFlight.endpoint}")
    private String deleteFlightEndpoint;

    @Override
    public ApiResponseMessage postFlight(Flight flight) {

	try {
	    ResponseEntity<ApiResponseMessage> addFlightRepo = restTemplate.exchange(addFlightsEndpoint,
		    HttpMethod.POST, new HttpEntity<Flight>(flight, this.buildHeaders()), ApiResponseMessage.class);

	    return addFlightRepo.getBody();
	} catch (HttpClientErrorException | HttpServerErrorException e) {
	    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
		throw new AirportServiceException(HttpStatus.NOT_FOUND.value(),
			"Flight exist, Unable to add duplicate flight.");
	    }
	    if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
		throw new AirportServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
			"Unable to add flight due to unexpected error");
	    }
	}
	return null;
    }

    @Override
    public ApiResponseMessage postMeals(Flight flight, Meals meals) {

	Map<String, Object> params = new HashMap<>();
	params.put("flightNumber", flight.getFlightNumber());
	params.put("flightDepartureDate", flight.getFlightDepartureDate());

	UriComponents uriComponents = UriComponentsBuilder.fromUriString(addMealsEndpoint).buildAndExpand(params);

	try {
	    ResponseEntity<ApiResponseMessage> addFlightRepo = restTemplate.exchange(uriComponents.toUri().toString(),
		    HttpMethod.POST, new HttpEntity<Meals>(meals, this.buildHeaders()), ApiResponseMessage.class);

	    return addFlightRepo.getBody();
	} catch (HttpClientErrorException  | HttpServerErrorException e) {
	    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
		throw new AirportServiceException(HttpStatus.NOT_FOUND.value(),
			"Flight deosn't exist, please pass correct flight details");
	    }
	    if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
		throw new AirportServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
			"Unable to add meals due to unexpected error");
	    }
	}
	return null;
    }

    @Override
    public ApiResponseMessage deleteFlight(Flight flight) {

	Map<String, Object> params = new HashMap<>();
	params.put("flightNumber", flight.getFlightNumber());
	params.put("flightDepartureDate", flight.getFlightDepartureDate());

	
	UriComponents uriComponents = UriComponentsBuilder.fromUriString(deleteFlightEndpoint).buildAndExpand(params);

	try {
	    ResponseEntity<ApiResponseMessage> addFlightRepo = restTemplate.exchange(uriComponents.toUri().toString(),
		    HttpMethod.DELETE, new HttpEntity<>(this.buildHeaders()), ApiResponseMessage.class);
	    
	    
	    System.out.println(addFlightRepo.getStatusCode().value() + " ........");
	    return addFlightRepo.getBody();
	} catch (HttpClientErrorException | HttpServerErrorException e) {
	    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
		throw new AirportServiceException(HttpStatus.NOT_FOUND.value(),
			"Flight deosn't exist, please pass correct flight details");
	    }
	    if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
		throw new AirportServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
			"Unable to delete flight due to unexpected error");
	    }
	}
	return null;

    }

    private HttpHeaders buildHeaders() {

	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

	return headers;
    }
}
