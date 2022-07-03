package com.proyecto.bookingrestaurantapi.services.Impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.bookingrestaurantapi.entities.Restaurant;
import com.proyecto.bookingrestaurantapi.entities.Turn;
import com.proyecto.bookingrestaurantapi.exceptions.BookingException;
import com.proyecto.bookingrestaurantapi.exceptions.InternalServerErrorException;
import com.proyecto.bookingrestaurantapi.exceptions.NotFountException;
import com.proyecto.bookingrestaurantapi.jsons.CreateRestaurantRest;
import com.proyecto.bookingrestaurantapi.jsons.RestaurantRest;
import com.proyecto.bookingrestaurantapi.jsons.TurnRest;
import com.proyecto.bookingrestaurantapi.repositories.RestaurantRepository;
import com.proyecto.bookingrestaurantapi.repositories.TurnRepository;
import com.proyecto.bookingrestaurantapi.services.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	private TurnRepository turnRepository; 
	
	
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


	@Override
	public String createRestaurant(CreateRestaurantRest createRestaurantRest) throws BookingException {
	
		final  Restaurant restaurant = new Restaurant();
		restaurant.setName(createRestaurantRest.getName());
		restaurant.setDescription(createRestaurantRest.getDescription());
		restaurant.setAddress(createRestaurantRest.getAddress());
		restaurant.setImage(createRestaurantRest.getImage());
		restaurant.setPrice(createRestaurantRest.getPrice());
		
		try {
			restaurantRepository.save(restaurant);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR",e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}			
		return "RestaurantOK";
	}


	@Override
	public String deleteRestaurant(String name) throws BookingException {
		
		Restaurant restauranr = restaurantRepository.findByName(name)
				.orElseThrow(() -> new NotFountException("NAME_NOT_FUND","NAME_NOT_FOUND"));
		try {
			restaurantRepository.deleteByName(name);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR",e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return "NAME_DELETED";
	}


//	@Override
//	public String createTurn(TurnRest createTurn) throws BookingException {
//		final Turn turnId = turnRepository.findById(createTurn.getId())
//				.orElseThrow(() -> new NotFountException("TURN_NOT_FUND","TURN_NOT_FOUND"));
//		
//		final Restaurant restaurantid = restaurantRepository.findById(createTurn.getId())
//				.orElseThrow(() -> new NotFountException("RESTAURANT_NOT_FUND","RESTAURANT_NOT_FOUND"));
//		
//		final Turn turn = new Turn();
//		turn.setDescription(createTurn.getDescription());
//		turn.setRestaurant(restaurantid);
//		
//		try {
//			turnRepository.save(turn);
//		} catch (Exception e) {
//			LOGGER.error("INTERNAL_SERVER_ERROR",e);
//			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
//		}
//		
//		return "CREATE OK";
//	}


	


	



}
