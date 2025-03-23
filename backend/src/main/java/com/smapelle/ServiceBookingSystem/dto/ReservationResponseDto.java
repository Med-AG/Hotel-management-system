package com.smapelle.ServiceBookingSystem.dto;

import java.util.List;

import lombok.Data;

@Data
public class ReservationResponseDto {
	

	private List<ReservationDto> reservationDtolList;
	
	private Integer totalPages;
	
	private Integer pageNumber;
	
}
