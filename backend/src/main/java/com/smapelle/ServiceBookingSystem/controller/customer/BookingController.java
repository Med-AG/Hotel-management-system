package com.smapelle.ServiceBookingSystem.controller.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smapelle.ServiceBookingSystem.dto.ReservationDto;
import com.smapelle.ServiceBookingSystem.services.customer.booking.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class BookingController {

	private final BookingService bookingService;
	
	
	@PostMapping("/book")
	public ResponseEntity<?> postBooking(@RequestBody ReservationDto reservationDto) {
		boolean success = bookingService.postReservation(reservationDto);
		if (success) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/bookings/{userId}/{pageNumber}")
	public ResponseEntity<?> getAllBookingsByUserId(@PathVariable long userId, @PathVariable int pageNumber) {
		try {
			return ResponseEntity.ok(bookingService.getAllReservationsByUser(userId, pageNumber));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
		}
	}
	
}
