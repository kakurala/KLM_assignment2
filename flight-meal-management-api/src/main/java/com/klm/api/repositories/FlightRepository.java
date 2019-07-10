package com.klm.api.repositories;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klm.api.model.Flight;
import com.klm.api.model.Meal;
import com.klm.api.model.MealEntity;
import com.klm.api.model.Meals;

@Repository
@Transactional
public interface FlightRepository extends JpaRepository<Flight, String> {

	public default void addMeals(Meals meals, Flight flight) {

		ArrayList<MealEntity> entityList = new ArrayList<>();

		for (Meal meal : meals.getMeals()) {

			entityList.add(getNewEntity(flight, meal, "DINNER", meal.getDinner()));
			entityList.add(getNewEntity(flight, meal, "BREAKFAST", meal.getBreakfast()));
			entityList.add(getNewEntity(flight, meal, "LIGHT_SNACK", meal.getLightSnack()));
			entityList.add(getNewEntity(flight, meal, "LUNCH", meal.getLunch()));

		}
		
		flight.setMeals(entityList);
		
		this.save(flight);
	}

	public default MealEntity getNewEntity(Flight flight, Meal meal, String type, Integer numberOfMeals) {
		MealEntity entity = new MealEntity();

		entity.setFlightId(flight);
		entity.setMealClass(meal.getMealClass().name());

		entity.setMealType(type);
		entity.setNumberOfMeals(numberOfMeals);

		return entity;
	}
}
