package com.klm.api.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Flight implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id; // flightNumber + flightDepartureDate

	@JsonProperty("flightNumber")
	private String flightNumber = null;

	@JsonProperty("flightDepartureDate")
	private Date flightDepartureDate = null;

	@OneToMany(cascade = {CascadeType.ALL})
	public Collection<MealEntity> meals;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Collection<MealEntity> getMeals() {
		return meals;
	}

	public void setMeals(Collection<MealEntity> meals) {
		this.meals = meals;
	}

	public Flight flightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
		return this;
	}

	/**
	 * Get flightNumber
	 * 
	 * @return flightNumber
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Flight flightDepartureDate(Date flightDepartureDate) {
		this.flightDepartureDate = flightDepartureDate;
		return this;
	}

	/**
	 * Get flightDepartureDate
	 * 
	 * @return flightDepartureDate
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public Date getFlightDepartureDate() {
		return flightDepartureDate;
	}

	public void setFlightDepartureDate(Date flightDepartureDate) {
		this.flightDepartureDate = flightDepartureDate;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Flight flight = (Flight) o;
		return Objects.equals(this.flightNumber, flight.flightNumber)
				&& Objects.equals(this.flightDepartureDate, flight.flightDepartureDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(flightNumber, flightDepartureDate);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Flight {\n");

		sb.append("    flightNumber: ").append(toIndentedString(flightNumber)).append("\n");
		sb.append("    flightDepartureDate: ").append(toIndentedString(flightDepartureDate)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
