package com.klm.backend.klmbackend.model;



import java.util.ArrayList;
import java.util.List;
 
public class Meals {
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
 
  public List<Meal> getMeals() {
    return meals;
  }

  public void setMeals(List<Meal> meals) {
    this.meals = meals;
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

