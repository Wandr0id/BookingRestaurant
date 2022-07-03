package com.proyecto.bookingrestaurantapi.services;

import com.proyecto.bookingrestaurantapi.exceptions.BookingException;

public interface CancelReservationService {
	
	public String deleteReservation(String locator)throws BookingException;

}
