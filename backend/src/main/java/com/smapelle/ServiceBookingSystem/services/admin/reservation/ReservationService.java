package com.smapelle.ServiceBookingSystem.services.admin.reservation;

import com.smapelle.ServiceBookingSystem.dto.ReservationResponseDto;

public interface ReservationService {

	ReservationResponseDto getAllReservations(int pageNumber);

	boolean changeReservationStatus(long id, String status);

}
