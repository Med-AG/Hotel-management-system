package com.smapelle.ServiceBookingSystem.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.smapelle.ServiceBookingSystem.dto.ReservationDto;
import com.smapelle.ServiceBookingSystem.dto.RoomDto;
import com.smapelle.ServiceBookingSystem.entity.Reservation;
import com.smapelle.ServiceBookingSystem.entity.Room;

@Service
public class ReservationMapperImpl implements ReservationMapper {
	
	@Override
	public ReservationDto fromReservation(Reservation reservation) {
		ReservationDto reservationDto = new ReservationDto();
		BeanUtils.copyProperties(reservation, reservationDto);
		
		reservationDto.setUserId(reservation.getUser().getId());
		reservationDto.setUserName(reservation.getUser().getName());
		
		reservationDto.setRoomId(reservation.getRoom().getId());
		reservationDto.setRoomName(reservation.getRoom().getName());
		reservationDto.setRoomType(reservation.getRoom().getType());
		
		return reservationDto;
	}
	
}
