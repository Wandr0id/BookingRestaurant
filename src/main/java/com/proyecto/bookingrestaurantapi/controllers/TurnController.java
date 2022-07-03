package com.proyecto.bookingrestaurantapi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.bookingrestaurantapi.exceptions.BookingException;
import com.proyecto.bookingrestaurantapi.jsons.CreateTurnRest;
import com.proyecto.bookingrestaurantapi.jsons.TurnRest;
import com.proyecto.bookingrestaurantapi.responses.BookingResponse;
import com.proyecto.bookingrestaurantapi.services.TurnService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/booking-restaurant" + "/v1")
public class TurnController {
	
	
	@Autowired
	TurnService turnService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "createTurn", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
	public BookingResponse<String> createTurn(@RequestBody CreateTurnRest createTurnRest) throws BookingException{
		return new BookingResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK", turnService.createTurn(createTurnRest));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "turn",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<List<TurnRest>> getTurn() throws BookingException{
		return new BookingResponse<List<TurnRest>>("Succes", String.valueOf(HttpStatus.OK),"OK", turnService.getTurnRests());
		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "deleteTurn" + "/{turnId" + "}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteTurn(@PathVariable("turnId") Long turnId){
		boolean flag = turnService.delete(turnId);
		if(flag) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
}
