package com.proyecto.bookingrestaurantapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.bookingrestaurantapi.exceptions.BookingException;
import com.proyecto.bookingrestaurantapi.jsons.CreateRestaurantRest;
import com.proyecto.bookingrestaurantapi.jsons.RestaurantRest;
import com.proyecto.bookingrestaurantapi.responses.BookingResponse;
import com.proyecto.bookingrestaurantapi.services.RestaurantService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/booking-restaurant" + "/v1")
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "restaurant" + "/{restaurantId" + "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<RestaurantRest> getRestaurantById(@PathVariable Long restaurantId) throws BookingException{
		return new BookingResponse<>("Succes", String.valueOf(HttpStatus.OK),"OK", 
				restaurantService.getRestaurantById(restaurantId));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "restaurants",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<List<RestaurantRest>> getRestaurants() throws BookingException{
		return new BookingResponse<List<RestaurantRest>>("Succes",String.valueOf(HttpStatus.OK),"OK", restaurantService.getRestaurants());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "createRestaurant", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
	public BookingResponse<String> createRestaurant(@RequestBody CreateRestaurantRest createRestaurantRest) throws BookingException{
		return new BookingResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",restaurantService.createRestaurant(createRestaurantRest));
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/deleteRestaurant", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE )
	public BookingResponse<String> deleteRestaurant(@RequestParam String name)throws BookingException{
		return new BookingResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK", restaurantService.deleteRestaurant(name));
	}
	

}
