package com.klm.backend.klmbackend.service;

import com.klm.backend.klmbackend.model.ApiResponseMessage;
import com.klm.backend.klmbackend.model.Flight;
import com.klm.backend.klmbackend.model.Meals;

public interface AirportService {

	public ApiResponseMessage postFlight(Flight flight);

	public ApiResponseMessage postMeals(Flight flight, Meals meals);

	public ApiResponseMessage deleteFlight(Flight flight);

}
