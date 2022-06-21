package com.proyecto.bookingrestaurantapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.bookingrestaurantapi.entities.Turn;

@Repository
public interface TurnRepository  extends JpaRepository<Turn, Long>{
	
	Optional<Turn> findById(Long id);

}
