package com.smapelle.ServiceBookingSystem.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.smapelle.ServiceBookingSystem.dto.UserDto;
import com.smapelle.ServiceBookingSystem.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class User implements UserDetails{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	
	private String password;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(userRole.name()));
	}

	@Override
	public String getUsername() {
		return email;
	}

	public UserDto getUserDto() {
		UserDto dto = new UserDto();
		dto.setId(id);
		dto.setName(name);
		dto.setEmail(email);
		dto.setUserRole(userRole);
		return dto;
	}
}


