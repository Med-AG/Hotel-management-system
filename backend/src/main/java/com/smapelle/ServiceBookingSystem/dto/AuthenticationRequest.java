package com.smapelle.ServiceBookingSystem.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

	private String email;
	private String password;
	
}
