package com.proyecto.bookingrestaurantapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto.bookingrestaurantapi.entities.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{
	
	Optional<Notification> findByTemplateCode(String templateCode);

}
