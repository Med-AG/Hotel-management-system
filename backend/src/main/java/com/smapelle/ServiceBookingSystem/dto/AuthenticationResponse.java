package com.smapelle.ServiceBookingSystem.dto;

import com.smapelle.ServiceBookingSystem.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {

	private String jwt;
	private Long userId;
	private UserRole userRole;
	
}
