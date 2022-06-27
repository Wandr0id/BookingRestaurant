package com.proyecto.bookingrestaurantapi.services.Impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.bookingrestaurantapi.entities.Reservation;
import com.proyecto.bookingrestaurantapi.entities.Restaurant;
import com.proyecto.bookingrestaurantapi.entities.Turn;
import com.proyecto.bookingrestaurantapi.exceptions.BookingException;
import com.proyecto.bookingrestaurantapi.exceptions.InternalServerErrorException;
import com.proyecto.bookingrestaurantapi.exceptions.NotFountException;
import com.proyecto.bookingrestaurantapi.jsons.CreateReservationRest;
import com.proyecto.bookingrestaurantapi.jsons.ReservationRest;
import com.proyecto.bookingrestaurantapi.repositories.ReservationRepository;
import com.proyecto.bookingrestaurantapi.repositories.RestaurantRepository;
import com.proyecto.bookingrestaurantapi.repositories.TurnRepository;
import com.proyecto.bookingrestaurantapi.services.EmailService;
import com.proyecto.bookingrestaurantapi.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private TurnRepository turnRepository; 
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private EmailService emailService;

	@Override
	public ReservationRest getReservation(Long reservationId) throws BookingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createReservation(CreateReservationRest createReservationRest) throws BookingException {
		final Restaurant restaurantId = restaurantRepository.findById(createReservationRest.getIdRestaurant())
				.orElseThrow(() -> new NotFountException("RESTAURANT_NOT_FUND","RESTAURANT_NOT_FOUND"));
		
		final Turn turn = turnRepository.findById(createReservationRest.getTurnId())
				.orElseThrow(() -> new NotFountException("TURN_NOT_FUND","TURN_NOT_FOUND"));
		
		String locator =  generateLocator(restaurantId, createReservationRest);
		final Reservation reservation = new Reservation();
		reservation.setLocator(locator);
		reservation.setPerson(createReservationRest.getPerson());
		reservation.setDate(createReservationRest.getDate());
		reservation.setRestaurant(restaurantId);
		reservation.setTurn(turn.getDescription());
		reservation.setName(createReservationRest.getName());
		reservation.setEmail(createReservationRest.getEmail());
		
		
		try {
			reservationRepository.save(reservation);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR",e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}		
		
		this.emailService.processSendEmail(createReservationRest.getEmail(), "RESERVATION", createReservationRest.getName());		
		return locator;
	}
	
	@Override
	public void updateReservation(Boolean payment, String locator) throws BookingException {
		final Reservation reservation = reservationRepository.findByLocator(locator).orElseThrow(() -> new NotFountException("RESTAURANT_NOT_FUND","RESTAURANT_NOT_FOUND"));
		reservation.setPayment(true);
		try {
			reservationRepository.save(reservation);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR",e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		
	}

	private String generateLocator(Restaurant restaurantId, CreateReservationRest createReservationRest) throws BookingException{
		return restaurantId.getName()+createReservationRest.getTurnId();
	}

	



}
