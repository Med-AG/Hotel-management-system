package com.smapelle.ServiceBookingSystem.dto;

import java.time.LocalDate;

import com.smapelle.ServiceBookingSystem.enums.ReservationStatus;

import lombok.Data;

@Data
public class ReservationDto {
	
	private Long id;
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	private Long price;
	
	private ReservationStatus reservationStatus;
	
	private Long roomId;
	
	private String roomType;

	private String roomName;
	
	private Long userId;
	
	private String userName;

}
