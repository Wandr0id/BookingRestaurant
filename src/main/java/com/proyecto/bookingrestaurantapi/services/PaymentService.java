package com.proyecto.bookingrestaurantapi.services;

import com.proyecto.bookingrestaurantapi.exceptions.BookingException;
import com.proyecto.bookingrestaurantapi.jsons.PaymentConfirmRest;
import com.proyecto.bookingrestaurantapi.jsons.PaymentIntentRest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface PaymentService {
	
	public PaymentIntent paymentIntent(PaymentIntentRest paymentIntentRest) throws StripeException;
	
	public PaymentIntent paymentConfirm(PaymentConfirmRest paymentConfirmRest) throws StripeException, BookingException;
	
	public PaymentIntent paymentCancel(String paymentId) throws StripeException;

}
