package com.smapelle.ServiceBookingSystem.mapper;

import com.smapelle.ServiceBookingSystem.dto.ReservationDto;
import com.smapelle.ServiceBookingSystem.entity.Reservation;

public interface ReservationMapper {

	ReservationDto fromReservation(Reservation reservation);

}
