package com.smapelle.ServiceBookingSystem.services.admin.rooms;

import com.smapelle.ServiceBookingSystem.dto.RoomDto;
import com.smapelle.ServiceBookingSystem.dto.RoomsResponseDto;

public interface RoomsService {

	boolean postRoom(RoomDto roomDto);

	RoomsResponseDto getAllRooms(int pageNumber);

	RoomDto getRoomById(Long id);

	boolean updateRoom(long id, RoomDto roomDto);

	void deleteRoom(long id);

}
