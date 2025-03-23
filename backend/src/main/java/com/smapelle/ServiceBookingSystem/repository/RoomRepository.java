package com.smapelle.ServiceBookingSystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.smapelle.ServiceBookingSystem.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	Page<Room> findByAvailable(boolean available, Pageable pageable);
	
}
