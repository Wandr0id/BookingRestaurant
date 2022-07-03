package com.proyecto.bookingrestaurantapi.services;

import java.util.List;
import com.proyecto.bookingrestaurantapi.exceptions.BookingException;
import com.proyecto.bookingrestaurantapi.jsons.CreateTurnRest;
import com.proyecto.bookingrestaurantapi.jsons.TurnRest;

public interface TurnService {
	
	public List<TurnRest> getTurnRests() throws BookingException;
	
	String createTurn(CreateTurnRest createTurnRest) throws BookingException;
	
	public boolean delete(long id);

}
