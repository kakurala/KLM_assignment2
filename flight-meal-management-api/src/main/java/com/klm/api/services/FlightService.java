package com.klm.api.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klm.api.APIMessages;
import com.klm.api.exceptions.FlightExistException;
import com.klm.api.exceptions.FlightNotFoundException;
import com.klm.api.model.ApiResponseMessage;
import com.klm.api.model.Flight;
import com.klm.api.model.Meal;
import com.klm.api.model.MealEntity;
import com.klm.api.model.Meals;
import com.klm.api.repositories.FlightRepository;

@Service
public class FlightService {

    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    private FlightRepository flightRepo;

    public void addMeals(Meals meals, Flight flight) {

	ArrayList<MealEntity> entityList = new ArrayList<>();

	for (Meal meal : meals.getMeals()) {

	    entityList.add(getNewEntity(flight, meal, "DINNER", meal.getDinner()));
	    entityList.add(getNewEntity(flight, meal, "BREAKFAST", meal.getBreakfast()));
	    entityList.add(getNewEntity(flight, meal, "LIGHT_SNACK", meal.getLightSnack()));
	    entityList.add(getNewEntity(flight, meal, "LUNCH", meal.getLunch()));

	}

	flight.setMeals(entityList);

	flightRepo.save(flight);
    }

    private MealEntity getNewEntity(Flight flight, Meal meal, String type, Integer numberOfMeals) {
	MealEntity entity = new MealEntity();

	entity.setFlightId(flight);
	entity.setMealClass(meal.getMealClass().name());

	entity.setMealType(type);
	entity.setNumberOfMeals(numberOfMeals);

	return entity;
    }

    public ApiResponseMessage addNewFlight(Flight flight) throws FlightExistException {

	final String flightId = generatePrimaryKey(flight.getFlightNumber(), flight.getFlightDepartureDate());

	Optional<Flight> flightInDB = flightRepo.findById(flightId);

	logger.info("Flight found while adding new flight {}", flightInDB.isPresent());

	if (flightInDB.isPresent()) {
	    throw new FlightExistException(new ApiResponseMessage(1, APIMessages.FLIGHT_ALREADY_EXIST));
	}

	flight.setId(generatePrimaryKey(flight.getFlightNumber(), flight.getFlightDepartureDate()));

	flightRepo.save(flight);

	return new ApiResponseMessage(4, APIMessages.FLIGHT_ADDED_SUCCESSFULLY);
    }

    public ApiResponseMessage addMealsToFlight(String flightNumber, Date flightDepartureDate, @Valid Meals meals)
	    throws FlightNotFoundException {

	final String flightId = generatePrimaryKey(flightNumber, flightDepartureDate);

	Optional<Flight> flightInDB = flightRepo.findById(flightId);

	flightInDB.orElseThrow(
		() -> new FlightNotFoundException(new ApiResponseMessage(1, APIMessages.FLIGHT_NOT_FOUND)));

	// call addMeals method to transform pojo bean to entity beans
	this.addMeals(meals, flightInDB.get());

	return new ApiResponseMessage(4, APIMessages.MEALS_ADDED_SUCCESSFULLY);
    }

    public ApiResponseMessage deleteFlight(String flightNumber, Date flightDepartureDate)
	    throws FlightNotFoundException {

	final String flightId = generatePrimaryKey(flightNumber, flightDepartureDate);

	Optional<Flight> flightInDB = flightRepo.findById(flightId);

	flightInDB.orElseThrow(
		() -> new FlightNotFoundException(new ApiResponseMessage(1, APIMessages.FLIGHT_NOT_FOUND)));

	// DB call
	flightRepo.deleteById(flightId);

	return new ApiResponseMessage(4, String.format(APIMessages.FLIGHT_DELETED, flightNumber));

    }

    private String generatePrimaryKey(String number, Date date) {

	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	String dateString = format.format(date);

	String key = number.concat(dateString);

	return key;
    }

}
