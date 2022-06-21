package com.proyecto.bookingrestaurantapi.services;

import com.proyecto.bookingrestaurantapi.exceptions.BookingException;
import com.proyecto.bookingrestaurantapi.jsons.CreateReservationRest;
import com.proyecto.bookingrestaurantapi.jsons.ReservationRest;

public interface ReservationService {
	
	ReservationRest getReservation(Long reservationId) throws BookingException;
	
	String createReservation(CreateReservationRest createReservationRest) throws BookingException;

}
