package com.proyecto.bookingrestaurantapi.controller;






import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.proyecto.bookingrestaurantapi.controllers.RestaurantController;
import com.proyecto.bookingrestaurantapi.exceptions.BookingException;
import com.proyecto.bookingrestaurantapi.jsons.RestaurantRest;
import com.proyecto.bookingrestaurantapi.jsons.TurnRest;
import com.proyecto.bookingrestaurantapi.responses.BookingResponse;
import com.proyecto.bookingrestaurantapi.services.RestaurantService;



@DataJpaTest
public class RestaurantControllerTests {
	 
	private static final Long RESTAURANT_ID = 1L;
	private static final String NAME = "Buger";
	private static final String DESCRIPCION = "Todo tipo de hamburguesas";
	private static final String ADDRES = "Calle Morelli 139";
	private static final String IMAGEN = "ww.image.com";
	
	private static final String SUCCES_STATUS = "SUCCES";
	private static final String SUCCES_CODE = "200 OK";
	private static final String OK = "OK";
	
	private static final List<TurnRest> TURN_LIST = new ArrayList<>();
	private static final RestaurantRest  RESTAURANT_REST = new RestaurantRest(); 
	private static final List<RestaurantRest> RESTAURANT_REST_LIST = new ArrayList<>();
	
	@Mock 
	RestaurantService restaurantService;
	
	@InjectMocks 
	RestaurantController restaurantController;
	 
	
	@Before
	public void init() throws BookingException{
		MockitoAnnotations.initMocks(this);
		//MockitoAnnotations.openMocks(this);
		RESTAURANT_REST.setName(NAME);
		RESTAURANT_REST.setDescription(DESCRIPCION);
		RESTAURANT_REST.setAddress(ADDRES);
		RESTAURANT_REST.setImage(IMAGEN);
		RESTAURANT_REST.setTurns(TURN_LIST);
		
	 Mockito.when(restaurantService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT_REST);
		
		
	}
	

	@Test
	public void getRestaurantByIdTest() throws BookingException{
		final BookingResponse<RestaurantRest> response= restaurantController.getRestaurantById(RESTAURANT_ID);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESTAURANT_REST);
	}
	
	
	@Test
	public void getRestaurantsTest() throws BookingException{
		final BookingResponse<List<RestaurantRest>> response = restaurantController.getRestaurants();
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESTAURANT_REST_LIST);
	}

}
