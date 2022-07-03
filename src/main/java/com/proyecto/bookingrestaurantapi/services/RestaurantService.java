package com.proyecto.bookingrestaurantapi.services;

import java.util.List;

import com.proyecto.bookingrestaurantapi.exceptions.BookingException;
import com.proyecto.bookingrestaurantapi.jsons.CreateRestaurantRest;
import com.proyecto.bookingrestaurantapi.jsons.RestaurantRest;

public interface RestaurantService {
	
	RestaurantRest getRestaurantById(Long restaurantId) throws BookingException;
	
	public List<RestaurantRest> getRestaurants() throws BookingException;
	
	String  createRestaurant(CreateRestaurantRest createRestaurantRest) throws BookingException;
	
	public String deleteRestaurant(String name)throws BookingException;
	
	//String createTurn(TurnRest createTurn) throws BookingException;
}
