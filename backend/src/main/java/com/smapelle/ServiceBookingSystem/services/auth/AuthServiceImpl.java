package com.smapelle.ServiceBookingSystem.services.auth;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smapelle.ServiceBookingSystem.dto.SignupRequest;
import com.smapelle.ServiceBookingSystem.dto.UserDto;
import com.smapelle.ServiceBookingSystem.entity.User;
import com.smapelle.ServiceBookingSystem.enums.UserRole;
import com.smapelle.ServiceBookingSystem.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final UserRepository userRepository;
	
	@PostConstruct
	public void createAnAdminAccount() {
		Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
		if (adminAccount.isEmpty()) {
			User user = new User();
			user.setEmail("admin@test.com");
			user.setName("Admin");
			user.setUserRole(UserRole.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
			System.out.println("Admin account created successfully");
		}else {
			System.out.println("Admin account already exist");
		}
	}
	
	@Override
	public UserDto createUser(SignupRequest signupRequest) {
		if (userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()) {
			throw new EntityExistsException("User already exist with email " + signupRequest.getEmail());
		}
		User user = new User();
		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setUserRole(UserRole.CUSTOMER);
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		User createdUser = userRepository.save(user);
		return createdUser.getUserDto();
	}

}
