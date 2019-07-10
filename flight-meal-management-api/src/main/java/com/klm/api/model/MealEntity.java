package com.klm.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MealEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flightId", referencedColumnName = "id", insertable = true, updatable = false)
	public Flight flightId;

	public String mealClass;

	public String mealType;

	public Integer numberOfMeals;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Flight getFlightId() {
		return flightId;
	}

	public void setFlightId(Flight flightId) {
		this.flightId = flightId;
	}

	public String getMealClass() {
		return mealClass;
	}

	public void setMealClass(String mealClass) {
		this.mealClass = mealClass;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public Integer getNumberOfMeals() {
		return numberOfMeals;
	}

	public void setNumberOfMeals(Integer numberOfMeals) {
		this.numberOfMeals = numberOfMeals;
	}

}
