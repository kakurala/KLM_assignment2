package com.klm.api.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klm.api.model.ApiResponseMessage;
import com.klm.api.model.Flight;
import com.klm.api.model.Meals;
import com.klm.api.services.FlightService;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightApiController.class)
public class FlightApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService service;

    @InjectMocks
    private FlightApiController controller;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
	    MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Before
    public void init() {
	MockitoAnnotations.initMocks(this);
	mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void postNewFlightAndGetOkResponse() throws Exception {

	ApiResponseMessage res = new ApiResponseMessage(4, "Success");

	when(service.addNewFlight(any(Flight.class))).thenReturn(res);

	String flight = "{\"flightNumber\":\"KL9999\",\"flightDepartureDate\":\"2020-10-10\"}";

	Flight fl = new ObjectMapper().readValue(flight, Flight.class);

	this.mockMvc.perform(post("/api/flight").contentType(MediaType.APPLICATION_JSON_UTF8).content(flight))
		.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.code", is(4)))
		.andExpect(jsonPath("$.type", is("ok"))).andExpect(jsonPath("$.message", is("Success")));

	verify(service, times(1)).addNewFlight(fl);
	verifyNoMoreInteractions(service);

    }

    @Test
    public void deleteExistingFlightAndGetOkResponse() throws Exception {

	ApiResponseMessage res = new ApiResponseMessage(4, "Success");

	when(service.deleteFlight(any(String.class), any(Date.class))).thenReturn(res);

//	String flight = "{\"flightNumber\":\"KL9999\",\"flightDepartureDate\":\"2020-10-10\"}";

	this.mockMvc
		.perform(delete("/api/flight/{flightNumber}/{flightDepartureDate}", "KL9999", "2020-10-10")
			.contentType(APPLICATION_JSON_UTF8))
		.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.code", is(4)))
		.andExpect(jsonPath("$.type", is("ok")));

	verify(service, times(1)).deleteFlight(any(String.class), any(Date.class));
	verifyNoMoreInteractions(service);
    }

    @Test
    public void addMealsTOFlightAndGetOkResponse() throws Exception {

	ApiResponseMessage res = new ApiResponseMessage(4, "Success");

	when(service.addMealsToFlight(any(String.class), any(Date.class), any(Meals.class))).thenReturn(res);

	String meals = "{  \"meals\": [    {      \"mealClass\": \"ECONOMY\",      \"breakfast\": 0,      \"lightSnack\": 1,      \"lunch\": 1,      \"dinner\": 1    },    {      \"mealClass\": \"BUSINESS\",      \"breakfast\": 0,      \"lightSnack\": 1,      \"lunch\": 1,      \"dinner\": 1    }  ]}";

	this.mockMvc
		.perform(post("/api/flight/{flightNumber}/{flightDepartureDate}/meals", "KL9999", "2020-10-10")
			.contentType(APPLICATION_JSON_UTF8).content(meals))
		.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.code", is(4)))
		.andExpect(jsonPath("$.type", is("ok")));

	verify(service, times(1)).addMealsToFlight(any(String.class), any(Date.class), any(Meals.class));
	verifyNoMoreInteractions(service);
    }

    static String asJsonString(final Object obj) {
	try {
	    return new ObjectMapper().writeValueAsString(obj);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }
}
