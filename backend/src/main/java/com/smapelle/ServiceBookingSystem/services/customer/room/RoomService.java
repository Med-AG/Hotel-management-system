package com.smapelle.ServiceBookingSystem.services.customer.room;

import com.smapelle.ServiceBookingSystem.dto.RoomsResponseDto;

public interface RoomService {

	RoomsResponseDto getAvailableRooms(int pageNumber);

}
