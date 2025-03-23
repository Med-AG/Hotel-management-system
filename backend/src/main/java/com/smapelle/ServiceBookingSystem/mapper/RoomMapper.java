package com.smapelle.ServiceBookingSystem.mapper;

import com.smapelle.ServiceBookingSystem.dto.RoomDto;
import com.smapelle.ServiceBookingSystem.entity.Room;

public interface RoomMapper {

	RoomDto fromRoom(Room room);

	Room fromRoomDto(RoomDto roomDto);

}
