package com.smapelle.ServiceBookingSystem.controller.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smapelle.ServiceBookingSystem.services.customer.room.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class roomController {
	
	private final RoomService roomService;
	
	@GetMapping("/rooms/{pageNumber}")
	public ResponseEntity<?> getAllRooms(@PathVariable int pageNumber) {
		return ResponseEntity.ok(roomService.getAvailableRooms(pageNumber));
	}
	
}
