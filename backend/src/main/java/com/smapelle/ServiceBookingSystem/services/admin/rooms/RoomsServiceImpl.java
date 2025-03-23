package com.smapelle.ServiceBookingSystem.services.admin.rooms;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.smapelle.ServiceBookingSystem.dto.RoomDto;
import com.smapelle.ServiceBookingSystem.dto.RoomsResponseDto;
import com.smapelle.ServiceBookingSystem.entity.Room;
import com.smapelle.ServiceBookingSystem.mapper.RoomMapper;
import com.smapelle.ServiceBookingSystem.repository.RoomRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService{

	private final RoomRepository roomRepository;
	private final RoomMapper roomMapper;
	
	@Override
	public boolean postRoom(RoomDto roomDto) {
		try {
			Room room = roomMapper.fromRoomDto(roomDto);
			room.setAvailable(true);
			roomRepository.save(room);
			return true;			
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public RoomsResponseDto getAllRooms(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, 6);
		Page<Room> roomPage = roomRepository.findAll(pageable);
		RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
		roomsResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
		roomsResponseDto.setTotalPages(roomPage.getTotalPages());
		roomsResponseDto.setRoomDtoList(roomPage.getContent().stream()
				.map(roomMapper::fromRoom).collect(Collectors.toList()));
		
		/* *** classic way ------------------------
		List<Room> roomList = roomPage.getContent();
		List<RoomDto> roomDtos = null;
		for (Room room : roomList) {
			RoomDto dto = roomMapper.fromRoom(room);
			roomDtos.add(dto);
		}
		roomsResponseDto.setRoomDtoList(roomDtos);
		*/
		
		return roomsResponseDto;
	}
	
	@Override
	public RoomDto getRoomById(Long id) {
		Optional<Room> room = roomRepository.findById(id);
		if (room.isPresent()) {
			return roomMapper.fromRoom(room.get());		
		}else {
			throw new EntityNotFoundException("Room not present.");
		}
	}
	
	@Override
	public boolean updateRoom(long id, RoomDto roomDto) {
		Optional<Room> optionalRoom = roomRepository.findById(id);
		if (optionalRoom.isPresent()) {
			Room existingRoom = optionalRoom.get();
			existingRoom.setName(roomDto.getName());
			existingRoom.setPrice(roomDto.getPrice());
			existingRoom.setType(roomDto.getType());
			roomRepository.save(existingRoom);
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void deleteRoom(long id) {
		Optional<Room> room = roomRepository.findById(id);
		if (room.isPresent()) {
			roomRepository.deleteById(id);
		}else {
			throw new EntityNotFoundException("Room not present.");
		}
	}
	
}
