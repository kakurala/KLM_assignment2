package com.klm.api.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompositeKey implements Serializable {

	@Column
	private String flightNumber = null;

	@Column
	private Date flightDepartureDate = null;

	public CompositeKey() {}
	
	public CompositeKey(String flightNumber, Date flightDepartureDate) {
		super();
		this.flightNumber = flightNumber;
		this.flightDepartureDate = flightDepartureDate;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Date getFlightDepartureDate() {
		return flightDepartureDate;
	}

	public void setFlightDepartureDate(Date flightDepartureDate) {
		this.flightDepartureDate = flightDepartureDate;
	}
	
}
