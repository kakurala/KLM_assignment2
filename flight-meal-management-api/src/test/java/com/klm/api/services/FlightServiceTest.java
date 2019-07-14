package com.klm.api.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klm.api.exceptions.FlightExistException;
import com.klm.api.exceptions.FlightNotFoundException;
import com.klm.api.model.ApiResponseMessage;
import com.klm.api.model.Flight;
import com.klm.api.model.Meals;
import com.klm.api.repositories.FlightRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceTest {

    @MockBean
    private FlightRepository flightRepo;

    @Autowired
    private FlightService service;

    private Flight flight;

    @Before
    public void init() {
	MockitoAnnotations.initMocks(this);

	String flight = "{\"flightNumber\":\"KL9990\",\"flightDepartureDate\":\"2020-10-10\"}";

	try {
	    this.flight = new ObjectMapper().readValue(flight, Flight.class);
	} catch (JsonParseException e) {
	    e.printStackTrace();
	} catch (JsonMappingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    @Test
    public void addFlightTest() throws FlightExistException {

	when(flightRepo.findById("KL999020201010")).thenReturn(Optional.empty()); // flight not exist
	when(flightRepo.save(flight)).thenReturn(flight);

	ApiResponseMessage res = service.addNewFlight(flight);

	assertEquals("ok", res.getType());
    }

    @Test(expected = FlightExistException.class)
    public void addFlightTestWithException() throws FlightExistException {

	when(flightRepo.findById("KL999020201010")).thenReturn(Optional.of(flight)); // flight exist
	when(flightRepo.save(flight)).thenReturn(flight);

	ApiResponseMessage res = service.addNewFlight(flight);

	assertEquals("ok", res.getType());
    }
    
    @Test
    public void addMealsTest() throws JsonParseException, JsonMappingException, IOException, FlightExistException,
	    FlightNotFoundException {

	String meals = "{  \"meals\": [    {      \"mealClass\": \"ECONOMY\",      \"breakfast\": 0,      \"lightSnack\": 1,      \"lunch\": 1,      \"dinner\": 1    },    {      \"mealClass\": \"BUSINESS\",      \"breakfast\": 0,      \"lightSnack\": 1,      \"lunch\": 1,      \"dinner\": 1    }  ]}";

	Meals mealsobj = new ObjectMapper().readValue(meals, Meals.class);

	when(flightRepo.findById("KL999020201010")).thenReturn(Optional.of(flight));
	when(flightRepo.save(flight)).thenReturn(flight);

	ApiResponseMessage res = service.addMealsToFlight(flight.getFlightNumber(), flight.getFlightDepartureDate(),
		mealsobj);

	assertEquals("ok", res.getType());
    }

    @Test(expected = FlightNotFoundException.class)
    public void addMealsTestWithException() throws JsonParseException, JsonMappingException, IOException,
	    FlightExistException, FlightNotFoundException {

	String meals = "{  \"meals\": [    {      \"mealClass\": \"ECONOMY\",      \"breakfast\": 0,      \"lightSnack\": 1,      \"lunch\": 1,      \"dinner\": 1    },    {      \"mealClass\": \"BUSINESS\",      \"breakfast\": 0,      \"lightSnack\": 1,      \"lunch\": 1,      \"dinner\": 1    }  ]}";

	Meals mealsobj = new ObjectMapper().readValue(meals, Meals.class);

	when(flightRepo.findById("KL999020201010")).thenReturn(Optional.empty()); // flight not found
	when(flightRepo.save(flight)).thenReturn(flight);

	ApiResponseMessage res = service.addMealsToFlight(flight.getFlightNumber(), flight.getFlightDepartureDate(),
		mealsobj);

	assertEquals("ok", res.getType());

    }

    @Test
    public void deleteFlightTest() throws FlightNotFoundException {

	when(flightRepo.findById("KL999020201010")).thenReturn(Optional.of(flight));
	doNothing().when(flightRepo).delete(flight);

	ApiResponseMessage res = service.deleteFlight(flight.getFlightNumber(), flight.getFlightDepartureDate());

	assertEquals("ok", res.getType());
    }

    @Test(expected = FlightNotFoundException.class)
    public void deleteFlightWithExceptionTest() throws FlightNotFoundException {

	when(flightRepo.findById("KL999020201010")).thenReturn(Optional.empty()); // flight not found
	doNothing().when(flightRepo).delete(flight);

	ApiResponseMessage res = service.deleteFlight(flight.getFlightNumber(), flight.getFlightDepartureDate());

	assertEquals("ok", res.getType());
    }
}
