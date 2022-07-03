package com.proyecto.bookingrestaurantapi.services.Impl;

import java.util.List;
import java.util.Optional;
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
import com.proyecto.bookingrestaurantapi.jsons.CreateReservationRest;
import com.proyecto.bookingrestaurantapi.jsons.CreateTurnRest;
import com.proyecto.bookingrestaurantapi.jsons.TurnRest;
import com.proyecto.bookingrestaurantapi.repositories.RestaurantRepository;
import com.proyecto.bookingrestaurantapi.repositories.TurnRepository;
import com.proyecto.bookingrestaurantapi.services.TurnService;
@Service
public class TurnServiceImpl implements TurnService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TurnServiceImpl.class);
	
	@Autowired
	TurnRepository turnRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public static final ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<TurnRest> getTurnRests() throws BookingException {
		final List<Turn> turnsEntity = turnRepository.findAll();
		return turnsEntity.stream().map(service -> modelMapper.map(service, TurnRest.class)).collect(Collectors.toList());
	}

	@Override
	public String createTurn(CreateTurnRest createTurnRest) throws BookingException {
		final Restaurant restaurantId = restaurantRepository.findById(createTurnRest.getRestaurantId())
				.orElseThrow(() -> new NotFountException("RESTAURANT_NOT_FUND","RESTAURANT_NOT_FOUND"));
		
		final Turn turn = new Turn();
		turn.setDescription(createTurnRest.getDescription());
		turn.setRestaurant(restaurantId);
		
		try {
			turnRepository.save(turn);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR",e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		
		return "TurnOK";
	}

	@Override
	public boolean delete(long id) {
		try {
			Optional<Turn> turno = turnRepository.findById(id);
			if(turno.isPresent()) {
				turnRepository.delete(turno.get());
			}else {
				System.out.println("ID no existe");
			}
			return true;
		} catch (Exception e) {
			System.out.println("fallo al borrar Turno");
			e.printStackTrace();
		}
		return false;
	}
	

		

}
