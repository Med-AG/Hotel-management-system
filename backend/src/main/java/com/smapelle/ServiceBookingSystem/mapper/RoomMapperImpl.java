package com.smapelle.ServiceBookingSystem.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.smapelle.ServiceBookingSystem.dto.RoomDto;
import com.smapelle.ServiceBookingSystem.entity.Room;

@Service
public class RoomMapperImpl implements RoomMapper {

	@Override
	public RoomDto fromRoom(Room room) {
		RoomDto roomDto = new RoomDto();
		BeanUtils.copyProperties(room, roomDto);
		return roomDto;
	}
	
	@Override
	public Room fromRoomDto(RoomDto roomDto) {
		Room room = new Room();
		BeanUtils.copyProperties(roomDto, room);
		return room;
	}
	
}
