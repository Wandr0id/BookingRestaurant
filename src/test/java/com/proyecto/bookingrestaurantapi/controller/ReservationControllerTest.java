package com.proyecto.bookingrestaurantapi.controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.proyecto.bookingrestaurantapi.controllers.ReservationController;
import com.proyecto.bookingrestaurantapi.exceptions.BookingException;
import com.proyecto.bookingrestaurantapi.jsons.CreateReservationRest;
import com.proyecto.bookingrestaurantapi.responses.BookingResponse;
import com.proyecto.bookingrestaurantapi.services.ReservationService;

public class ReservationControllerTest {
	
	
	private static final String SUCCES_STATUS = "SUCCES";
	private static final String SUCCES_CODE = "200 OK";
	private static final String OK = "OK";
	
	private static final Long RESTAURANT_ID = 1L;
	private static final Date DATE = new Date();
	private static final Long PERSON = 1L;
	private static final Long TURN_ID = 1L;
	private static final String LOCATOR = "Burger2";
	
	CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();
	
	@Mock
	ReservationService reservationService;
	
	@InjectMocks
	ReservationController reservationController;
	
	@Before
	public void init() throws BookingException{
		MockitoAnnotations.initMocks(this);
		CREATE_RESERVATION_REST.setDate(DATE); 
		CREATE_RESERVATION_REST.setPerson(PERSON);
		CREATE_RESERVATION_REST.setIdRestaurant(RESTAURANT_ID);
		CREATE_RESERVATION_REST.setTurnId(TURN_ID);
		
		Mockito.when(reservationService.createReservation(CREATE_RESERVATION_REST)).thenReturn(LOCATOR);
		
	}
	
	@Test
	public void createReservationTest() throws BookingException{
		BookingResponse<String> response = reservationController.createReservation(CREATE_RESERVATION_REST);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), LOCATOR);
	}

}
