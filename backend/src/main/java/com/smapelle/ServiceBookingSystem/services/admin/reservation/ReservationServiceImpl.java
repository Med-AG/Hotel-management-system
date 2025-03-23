package com.smapelle.ServiceBookingSystem.services.admin.reservation;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.smapelle.ServiceBookingSystem.dto.ReservationResponseDto;
import com.smapelle.ServiceBookingSystem.entity.Reservation;
import com.smapelle.ServiceBookingSystem.entity.Room;
import com.smapelle.ServiceBookingSystem.enums.ReservationStatus;
import com.smapelle.ServiceBookingSystem.mapper.ReservationMapper;
import com.smapelle.ServiceBookingSystem.repository.ReservationRepository;
import com.smapelle.ServiceBookingSystem.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	private final ReservationRepository reservationRepository;
	private final RoomRepository roomRepository;
	private final ReservationMapper reservationMapper;
	
	public static final int SEARCH_RESULT_PER_PAGE = 4;
	
	@Override
	public ReservationResponseDto getAllReservations(int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
		Page<Reservation> reservationPage = reservationRepository.findAll(pageable);
		
		ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
		reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
		reservationResponseDto.setTotalPages(reservationPage.getTotalPages());
		reservationResponseDto.setReservationDtolList(reservationPage.getContent().stream().map(reservationMapper::fromReservation).collect(Collectors.toList()));
		
		return reservationResponseDto;
	}
	
	@Override
	public boolean changeReservationStatus(long id, String status) {
		Optional<Reservation> optionalReservation = reservationRepository.findById(id);
		
		if (optionalReservation.isPresent()) {
			Reservation reservation = optionalReservation.get();
			if (Objects.equals(status, "Approve")) {
				reservation.setReservationStatus(ReservationStatus.APPROVED);
			}else {
				reservation.setReservationStatus(ReservationStatus.REJECTED);
			}
			reservationRepository.save(reservation);
			
			Room room = reservation.getRoom();
			room.setAvailable(false);
			roomRepository.save(room);
			
			return true;
		}
		return false;
	}
	
}
