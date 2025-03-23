package com.smapelle.ServiceBookingSystem.services.customer.booking;

import com.smapelle.ServiceBookingSystem.dto.ReservationDto;
import com.smapelle.ServiceBookingSystem.dto.ReservationResponseDto;

public interface BookingService {

	boolean postReservation(ReservationDto reservationDto);

	ReservationResponseDto getAllReservationsByUser(long userId, int pageNumber);

}
