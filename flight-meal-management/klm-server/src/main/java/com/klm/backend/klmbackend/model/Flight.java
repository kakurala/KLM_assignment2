package com.klm.backend.klmbackend.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Value;

/**
 * Flight
 */
@JsonInclude(NON_NULL)
@Value
public class Flight {

	private String flightNumber;
	private String flightDepartureDate;
}
