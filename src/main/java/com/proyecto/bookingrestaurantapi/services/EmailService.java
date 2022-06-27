package com.proyecto.bookingrestaurantapi.services;

import com.proyecto.bookingrestaurantapi.exceptions.BookingException;

public interface EmailService {
	
	public String processSendEmail(final String receiver, String templateCode, String currentName) throws BookingException;

}
