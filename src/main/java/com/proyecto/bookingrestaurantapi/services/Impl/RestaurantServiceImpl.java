package com.proyecto.bookingrestaurantapi.services.Impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.bookingrestaurantapi.entities.Restaurant;
import com.proyecto.bookingrestaurantapi.exceptions.BookingException;
import com.proyecto.bookingrestaurantapi.exceptions.NotFountException;
import com.proyecto.bookingrestaurantapi.jsons.RestaurantRest;
import com.proyecto.bookingrestaurantapi.repositories.RestaurantRepository;
import com.proyecto.bookingrestaurantapi.services.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	public static final ModelMapper modelMapper = new ModelMapper();
	 
	@Override
	public RestaurantRest getRestaurantById(Long restaurantId) throws BookingException {
		return modelMapper.map(getRestaurantEntity(restaurantId),RestaurantRest.class);
	}
	
	
	@Override
	public List<RestaurantRest> getRestaurants() throws BookingException {
		final List<Restaurant> restaurantsEntity = restaurantRepository.findAll();
		return restaurantsEntity.stream().map(service -> modelMapper.map(service, RestaurantRest.class)).collect(Collectors.toList());
	} 
	
	
	
	private Restaurant getRestaurantEntity(Long restaurantId) throws BookingException{
		return restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFountException("SNOT-404-1","RESTAURANT_NOTFOUND"));
	}




}
