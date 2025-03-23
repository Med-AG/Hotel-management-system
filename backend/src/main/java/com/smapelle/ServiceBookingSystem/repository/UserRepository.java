package com.smapelle.ServiceBookingSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smapelle.ServiceBookingSystem.entity.User;
import com.smapelle.ServiceBookingSystem.enums.UserRole;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findFirstByEmail(String email);
	
	Optional<User> findByUserRole(UserRole userRole);
}
