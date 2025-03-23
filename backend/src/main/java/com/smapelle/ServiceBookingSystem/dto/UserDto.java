package com.smapelle.ServiceBookingSystem.dto;

import com.smapelle.ServiceBookingSystem.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {

	private Long id;
	private String email;
	private String name;
	private UserRole userRole;
	
}
