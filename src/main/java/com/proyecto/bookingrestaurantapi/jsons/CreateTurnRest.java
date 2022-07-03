package com.proyecto.bookingrestaurantapi.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateTurnRest {
	
	@JsonProperty("name")
	private String description;
	
	@JsonProperty("restaurantId")
	private Long restaurantId;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	
	
	

}
