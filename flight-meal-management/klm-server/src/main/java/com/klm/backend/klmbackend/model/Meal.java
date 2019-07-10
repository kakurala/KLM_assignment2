package com.klm.backend.klmbackend.model;

/**
 * Meal
 */
public class Meal {
  /**
   * Gets or Sets mealClass
   */
  public enum MealClassEnum {
    ECONOMY("ECONOMY"),
    
    BUSINESS("BUSINESS");

    private String value;

    MealClassEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static MealClassEnum fromValue(String text) {
      for (MealClassEnum b : MealClassEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  private MealClassEnum mealClass = null;

  private Integer breakfast = null;

  private Integer lightSnack = null;

  private Integer lunch = null;

  private Integer dinner = null;

  public Meal mealClass(MealClassEnum mealClass) {
    this.mealClass = mealClass;
    return this;
  }

   /**
   * Get mealClass
   * @return mealClass
  **/
  public MealClassEnum getMealClass() {
    return mealClass;
  }

  public void setMealClass(MealClassEnum mealClass) {
    this.mealClass = mealClass;
  }

  public Meal breakfast(Integer breakfast) {
    this.breakfast = breakfast;
    return this;
  }

   /**
   * Get breakfast
   * @return breakfast
  **/
  public Integer getBreakfast() {
    return breakfast;
  }

  public void setBreakfast(Integer breakfast) {
    this.breakfast = breakfast;
  }

  public Meal lightSnack(Integer lightSnack) {
    this.lightSnack = lightSnack;
    return this;
  }

   /**
   * Get lightSnack
   * @return lightSnack
  **/
  public Integer getLightSnack() {
    return lightSnack;
  }

  public void setLightSnack(Integer lightSnack) {
    this.lightSnack = lightSnack;
  }

  public Meal lunch(Integer lunch) {
    this.lunch = lunch;
    return this;
  }

   /**
   * Get lunch
   * @return lunch
  **/
  public Integer getLunch() {
    return lunch;
  }

  public void setLunch(Integer lunch) {
    this.lunch = lunch;
  }

  public Meal dinner(Integer dinner) {
    this.dinner = dinner;
    return this;
  }

   /**
   * Get dinner
   * @return dinner
  **/
  public Integer getDinner() {
    return dinner;
  }

  public void setDinner(Integer dinner) {
    this.dinner = dinner;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Meal {\n");
    
    sb.append("    mealClass: ").append(toIndentedString(mealClass)).append("\n");
    sb.append("    breakfast: ").append(toIndentedString(breakfast)).append("\n");
    sb.append("    lightSnack: ").append(toIndentedString(lightSnack)).append("\n");
    sb.append("    lunch: ").append(toIndentedString(lunch)).append("\n");
    sb.append("    dinner: ").append(toIndentedString(dinner)).append("\n");
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

