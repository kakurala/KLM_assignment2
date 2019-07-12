package com.klm.api.controllers;

import java.sql.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.klm.api.exceptions.FlightExistException;
import com.klm.api.exceptions.FlightNotFoundException;
import com.klm.api.model.ApiResponseMessage;
import com.klm.api.model.Flight;
import com.klm.api.model.Meals;
import com.klm.api.services.FlightService;

import io.swagger.annotations.ApiParam;

@RestController
public class FlightApiController implements FlightApi {

    private static final Logger logger = LoggerFactory.getLogger(FlightApiController.class);
    
    @Autowired
    private FlightService flightService;

    public ApiResponseMessage addFlight(
	    @ApiParam(value = "Flight that needs to be added", required = true) @Valid @RequestBody Flight flight) throws FlightExistException {
	
	logger.info("add flight endpoint");
	
	return  flightService.addNewFlight(flight);
    }

    public ApiResponseMessage addMealsToFlight(
	    @ApiParam(value = "flight number", required = true) @PathVariable("flightNumber") String flightNumber,
	    @ApiParam(value = "departure date", required = true) @PathVariable("flightDepartureDate") Date flightDepartureDate,
	    @ApiParam(value = "", required = true) @Valid @RequestBody Meals meals) throws FlightNotFoundException {

	logger.info("add meals endpoint");
	
	return flightService.addMealsToFlight(flightNumber, flightDepartureDate, meals);
    }

    public ApiResponseMessage deleteFlight(
	    @ApiParam(value = "Flight Number to be deleted", required = true) @PathVariable("flightNumber") String flightNumber,
	    @ApiParam(value = "Flight Departure date to be deleted", required = true) @PathVariable("flightDepartureDate") Date flightDepartureDate) throws FlightNotFoundException {

	logger.info("Delete flight endpoint");
	
	return flightService.deleteFlight(flightNumber, flightDepartureDate);
    }

 
}
