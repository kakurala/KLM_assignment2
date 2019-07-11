package com.klm.api;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.klm.api.model.Flight;
import com.klm.api.model.Meals;
import com.klm.api.repositories.FlightRepository;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-07-09T10:47:01.324+05:30")

@Controller
public class FlightApiController implements FlightApi {

    @Autowired
    private FlightRepository flightRepo;

    public ResponseEntity<ApiResponseMessage> addFlight(
	    @ApiParam(value = "Flight that needs to be added", required = true) @Valid @RequestBody Flight body) {

	Optional<Flight> flight = flightRepo
		.findById(generatePrimaryKey(body.getFlightNumber(), body.getFlightDepartureDate()));

	if (flight.isPresent()) {
	    return new ResponseEntity<ApiResponseMessage>(
		    new ApiResponseMessage(1, "Unable to add; Flight already exist"), HttpStatus.OK);
	}

	body.setId(generatePrimaryKey(body.getFlightNumber(), body.getFlightDepartureDate()));

	flightRepo.save(body);

	return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(4, "Success"), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponseMessage> addMealsToFlight(
	    @ApiParam(value = "flight number", required = true) @PathVariable("flightNumber") String flightNumber,
	    @ApiParam(value = "departure date", required = true) @PathVariable("flightDepartureDate") Date flightDepartureDate,
	    @ApiParam(value = "", required = true) @Valid @RequestBody Meals body) {

	Optional<Flight> flight = flightRepo.findById(generatePrimaryKey(flightNumber, flightDepartureDate));

	System.out.println(flight.isPresent());

	if (flight.isPresent()) {
	    flightRepo.addMeals(body, flight.get());
	    return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(4, "Success"), HttpStatus.OK);
	}

	return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(1, "Flight not found"), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponseMessage> deleteFlight(
	    @ApiParam(value = "Flight Number to be deleted", required = true) @PathVariable("flightNumber") String flightNumber,
	    @ApiParam(value = "Flight Departure date to be deleted", required = true) @PathVariable("flightDepartureDate") Date flightDepartureDate) {

	Optional<Flight> flight = flightRepo.findById(generatePrimaryKey(flightNumber, flightDepartureDate));

	if (flight.isPresent()) {
	    flightRepo.deleteById(generatePrimaryKey(flightNumber, flightDepartureDate));
	    return new ResponseEntity<ApiResponseMessage>(
		    new ApiResponseMessage(4, "Flight " + flightNumber + " Deleted"), HttpStatus.OK);
	}

	return new ResponseEntity<ApiResponseMessage>(new ApiResponseMessage(1, "Flight not found!"), HttpStatus.OK);
    }

    public String generatePrimaryKey(String number, Date date) {

	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	String dateString = format.format(date);

	String key = number.concat(dateString);

	return key;
    }
}
