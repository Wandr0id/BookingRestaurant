package com.proyecto.bookingrestaurantapi.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.proyecto.bookingrestaurantapi.entities.Board;
import com.proyecto.bookingrestaurantapi.entities.Reservation;
import com.proyecto.bookingrestaurantapi.entities.Restaurant;
import com.proyecto.bookingrestaurantapi.entities.Turn;
import com.proyecto.bookingrestaurantapi.exceptions.BookingException;
import com.proyecto.bookingrestaurantapi.jsons.RestaurantRest;
import com.proyecto.bookingrestaurantapi.repositories.RestaurantRepository;
import com.proyecto.bookingrestaurantapi.services.Impl.RestaurantServiceImpl;


public class RestaurantServiceTest {
	
	private static final Long RESTAURANT_ID = 1L;
	private static final String NAME = "Buger";
	private static final String DESCRIPCION = "Todo tipo de hamburguesas";
	private static final String ADDRES = "Calle Morelli 139";
	private static final String IMAGEN = "ww.image.com";
	

	private static final Restaurant  RESTAURANT = new Restaurant();
	private static final List<Turn> TURN_LIST = new ArrayList<>();
	private static final List<Board> BOARD_LIST = new ArrayList<>();
	private static final List<Reservation> RESERVATION_LIST = new ArrayList<>();
	
	@Mock
	RestaurantRepository restaurantRepository;
	 
	@InjectMocks
	RestaurantServiceImpl restaurantService;
	
	@Before
	public void init() throws BookingException{
		MockitoAnnotations.initMocks(this);
		RESTAURANT.setName(NAME);
		RESTAURANT.setDescription(DESCRIPCION);
		RESTAURANT.setAddress(ADDRES);
		RESTAURANT.setImage(IMAGEN);
		RESTAURANT.setTurns(TURN_LIST);
		RESTAURANT.setBoards(BOARD_LIST);
		RESTAURANT.setReservations(RESERVATION_LIST);
	}
	
	@Test
	public void getRestaurantByIdTest() throws BookingException{
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(RESTAURANT));
		restaurantService.getRestaurantById(RESTAURANT_ID);
	}
	
	@Test(expected = BookingException.class)
	public void getRestaurantByIdTestError() throws BookingException{
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.empty());
		restaurantService.getRestaurantById(RESTAURANT_ID);
		fail();
	}
	
	@Test
	public void getRestaurantTest() throws BookingException{
		final Restaurant restaurant = new Restaurant();
		Mockito.when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant));
		final List<RestaurantRest> response = restaurantService.getRestaurants();
		assertNotNull(response);
		assertNotNull(response.isEmpty());
		assertEquals(response.size(), 1);
	}

}
