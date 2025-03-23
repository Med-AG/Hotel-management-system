package com.smapelle.ServiceBookingSystem.services.auth;

import com.smapelle.ServiceBookingSystem.dto.SignupRequest;
import com.smapelle.ServiceBookingSystem.dto.UserDto;

public interface AuthService {
	
	UserDto createUser(SignupRequest signupRequest);
}
