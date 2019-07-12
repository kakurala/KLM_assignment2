package com.klm.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class Meals   {
	
  @JsonProperty("meals")
  private List<Meal> meals = null;

  public Meals meals(List<Meal> meals) {
    this.meals = meals;
    return this;
  }

  public Meals addMealsItem(Meal mealsItem) {
    if (this.meals == null) {
      this.meals = new ArrayList<Meal>();
    }
    this.meals.add(mealsItem);
    return this;
  }

   /**
   * Get meals
   * @return meals
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Meal> getMeals() {
    return meals;
  }

  public void setMeals(List<Meal> meals) {
    this.meals = meals;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Meals meals = (Meals) o;
    return Objects.equals(this.meals, meals.meals);
  }

  @Override
  public int hashCode() {
    return Objects.hash(meals);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Meals {\n");
    
    sb.append("    meals: ").append(toIndentedString(meals)).append("\n");
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

