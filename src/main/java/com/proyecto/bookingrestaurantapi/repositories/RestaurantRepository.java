package com.proyecto.bookingrestaurantapi.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.bookingrestaurantapi.entities.Reservation;
import com.proyecto.bookingrestaurantapi.entities.Restaurant;
import com.proyecto.bookingrestaurantapi.entities.Turn;
import com.proyecto.bookingrestaurantapi.jsons.RestaurantRest;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	
	Optional<Restaurant> findById(Long id);
	
	Optional<Restaurant> findByName(String nameRestaurant);
	
	@Query("SELECT REST FROM Restaurant REST")
	public List<Restaurant> findRestaurants();

	RestaurantRest save(RestaurantRest restaurante);

	
	@Modifying
	@Transactional 
	Optional<Restaurant> deleteByName(String name);
	
	

}
