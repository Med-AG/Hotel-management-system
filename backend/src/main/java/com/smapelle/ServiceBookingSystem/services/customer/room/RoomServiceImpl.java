package com.smapelle.ServiceBookingSystem.services.customer.room;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.smapelle.ServiceBookingSystem.dto.RoomsResponseDto;
import com.smapelle.ServiceBookingSystem.entity.Room;
import com.smapelle.ServiceBookingSystem.mapper.RoomMapper;
import com.smapelle.ServiceBookingSystem.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
	
	private final RoomRepository roomRepository;
	private final RoomMapper roomMapper;
	
	@Override
	public RoomsResponseDto getAvailableRooms(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, 6);
		Page<Room> roomPage = roomRepository.findByAvailable(true, pageable);
		
		RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
		roomsResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
		roomsResponseDto.setTotalPages(roomPage.getTotalPages());
		roomsResponseDto.setRoomDtoList(roomPage.getContent().stream()
				.map(roomMapper::fromRoom).collect(Collectors.toList()));
		
		return roomsResponseDto;
	}
	
	
}
