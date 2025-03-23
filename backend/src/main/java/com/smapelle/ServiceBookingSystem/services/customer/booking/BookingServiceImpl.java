package com.smapelle.ServiceBookingSystem.services.customer.booking;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.smapelle.ServiceBookingSystem.dto.ReservationDto;
import com.smapelle.ServiceBookingSystem.dto.ReservationResponseDto;
import com.smapelle.ServiceBookingSystem.entity.Reservation;
import com.smapelle.ServiceBookingSystem.entity.Room;
import com.smapelle.ServiceBookingSystem.entity.User;
import com.smapelle.ServiceBookingSystem.enums.ReservationStatus;
import com.smapelle.ServiceBookingSystem.mapper.ReservationMapper;
import com.smapelle.ServiceBookingSystem.repository.ReservationRepository;
import com.smapelle.ServiceBookingSystem.repository.RoomRepository;
import com.smapelle.ServiceBookingSystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
	
	private final UserRepository userRepository;
	private final RoomRepository roomRepository;
	private final ReservationRepository reservationRepository;	
	private final ReservationMapper reservationMapper;
	
	public static final int SEARCH_RESULT_PER_PAGE = 4;
	
	
	@Override
	public boolean postReservation(ReservationDto reservationDto) {
		Long roomId = reservationDto.getRoomId();
		Optional<Room> room = roomRepository.findById(roomId);
		
		Long userId = reservationDto.getUserId();
		Optional<User> user = userRepository.findById(userId);
		
		if (room.isPresent() && user.isPresent()) {
			Reservation reservation = new Reservation();
			
			reservation.setRoom(room.get());
			reservation.setUser(user.get());
			reservation.setCheckInDate(reservationDto.getCheckInDate());
			reservation.setCheckOutDate(reservationDto.getCheckOutDate());
			reservation.setReservationStatus(ReservationStatus.PENDING);
			
			Long days = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(), reservationDto.getCheckOutDate());
			reservation.setPrice(room.get().getPrice() * days);
			
			reservationRepository.save(reservation);
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public ReservationResponseDto getAllReservationsByUser(long userId, int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
		Page<Reservation> reservationPage = reservationRepository.findAllByUserId(userId, pageable);
		
		ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
		reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
		reservationResponseDto.setTotalPages(reservationPage.getTotalPages());
		reservationResponseDto.setReservationDtolList(reservationPage.getContent().stream().map(reservationMapper::fromReservation).collect(Collectors.toList()));
		
		return reservationResponseDto;
		
	}
	

}
