package com.proyecto.bookingrestaurantapi.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.proyecto.bookingrestaurantapi.dtos.ErrorDto;

public class NotFountException extends BookingException{

	private static final long serialVersionUID = 1L;

	public NotFountException(String code, String message) {
		super(code, HttpStatus.NOT_FOUND.value(), message);
	}
	
	public NotFountException(String code, String message, ErrorDto data) {
		super(code, HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
	}





}
