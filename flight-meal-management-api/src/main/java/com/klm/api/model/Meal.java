package com.klm.api.model;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * Meal
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-07-09T10:47:01.324+05:30")

@JsonIgnoreProperties(ignoreUnknown = true)
public class Meal {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

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

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static MealClassEnum fromValue(String text) {
			for (MealClassEnum b : MealClassEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("mealClass")
	private MealClassEnum mealClass = null;

	@JsonProperty("breakfast")
	private Integer breakfast = null;

	@JsonProperty("lightSnack")
	private Integer lightSnack = null;

	@JsonProperty("lunch")
	private Integer lunch = null;

	@JsonProperty("dinner")
	private Integer dinner = null;

	public Meal mealClass(MealClassEnum mealClass) {
		this.mealClass = mealClass;
		return this;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * Get mealClass
	 * 
	 * @return mealClass
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

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
	 * 
	 * @return breakfast
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

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
	 * 
	 * @return lightSnack
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

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
	 * 
	 * @return lunch
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

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
	 * 
	 * @return dinner
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public Integer getDinner() {
		return dinner;
	}

	public void setDinner(Integer dinner) {
		this.dinner = dinner;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Meal meal = (Meal) o;
		return Objects.equals(this.mealClass, meal.mealClass) && Objects.equals(this.breakfast, meal.breakfast)
				&& Objects.equals(this.lightSnack, meal.lightSnack) && Objects.equals(this.lunch, meal.lunch)
				&& Objects.equals(this.dinner, meal.dinner);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mealClass, breakfast, lightSnack, lunch, dinner);
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
