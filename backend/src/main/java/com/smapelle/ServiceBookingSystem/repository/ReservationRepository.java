package com.smapelle.ServiceBookingSystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.smapelle.ServiceBookingSystem.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Page<Reservation> findAllByUserId(long userId, Pageable pageable);
	
	
	
}
