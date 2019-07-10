package com.klm.backend.klmbackend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.klm.backend.klmbackend.model.ApiResponseMessage;
import com.klm.backend.klmbackend.model.Flight;
import com.klm.backend.klmbackend.model.Meals;
import com.klm.backend.klmbackend.service.AirportService;

@CrossOrigin
@RestController
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	AirportService airportService;

	@RequestMapping(value = "/addflight", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ApiResponseMessage addFlight(@RequestBody Flight flight) {

		logger.info("/addflight endpoint {}", flight.getFlightDepartureDate());

		return airportService.postFlight(flight);
	}

	@RequestMapping(value = "/addmeals/{flightNumber}/{flightDepartureDate}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	public ApiResponseMessage addMealsToFlight(@PathVariable("flightNumber") String flightNumber,
			@PathVariable("flightDepartureDate") String flightDepartureDate, @RequestBody Meals meals) {

		Flight flight = new Flight(flightNumber, flightDepartureDate);

		return airportService.postMeals(flight, meals);
	}

	@RequestMapping(value = "/removeflight/{flightNumber}/{flightDepartureDate}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ApiResponseMessage deleteFlight(@PathVariable("flightNumber") String flightNumber,
			@PathVariable("flightDepartureDate") String flightDepartureDate) {

		Flight flight = new Flight(flightNumber, flightDepartureDate);

		return airportService.deleteFlight(flight);
	}

}
