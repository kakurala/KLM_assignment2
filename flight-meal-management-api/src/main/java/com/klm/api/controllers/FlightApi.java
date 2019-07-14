package com.klm.api.controllers;

import java.sql.Date;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.klm.api.exceptions.FlightExistException;
import com.klm.api.exceptions.FlightNotFoundException;
import com.klm.api.model.ApiResponseMessage;
import com.klm.api.model.Flight;
import com.klm.api.model.Meals;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "flight")
@RequestMapping(value = "/api")
public interface FlightApi {

    @ApiOperation(value = "Add a new flight to the store", notes = "", response = String.class, tags = { "flights", })
    @ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input", response = String.class) })

    @RequestMapping(value = "/flight", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
	    MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
    ApiResponseMessage addFlight(
	    @ApiParam(value = "Flight that needs to be added", required = true) @Valid @RequestBody Flight body)
	    throws FlightExistException;

    @ApiOperation(value = "add meals to a flight on a date", notes = "", response = String.class, tags = { "flights", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
	    @ApiResponse(code = 405, message = "Invalid input", response = String.class) })

    @RequestMapping(value = "/flight/{flightNumber}/{flightDepartureDate}/meals", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
	    MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
    ApiResponseMessage addMealsToFlight(
	    @ApiParam(value = "flight number", required = true) @PathVariable("flightNumber") String flightNumber,
	    @ApiParam(value = "departure date", required = true) @PathVariable("flightDepartureDate") Date flightDepartureDate,
	    @ApiParam(value = "", required = true) @Valid @RequestBody Meals body) throws FlightNotFoundException;

    @ApiOperation(value = "Delete flights and meals in it.", notes = "", response = Void.class, tags = { "flights", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "success", response = Void.class),
	    @ApiResponse(code = 405, message = "Invalid input", response = Void.class) })

    @RequestMapping(value = "/flight/{flightNumber}/{flightDepartureDate}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
	    MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE)
    ApiResponseMessage deleteFlight(
	    @ApiParam(value = "delete flight", required = true) @PathVariable("flightNumber") String flightNumber,
	    @ApiParam(value = "Updated name of the pet", required = true) @PathVariable("flightDepartureDate") Date flightDepartureDate)
	    throws FlightNotFoundException;

}
