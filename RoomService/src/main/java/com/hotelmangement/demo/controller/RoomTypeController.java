package com.hotelmangement.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelmangement.demo.dto.RoomTypeDto;
import com.hotelmangement.demo.service.RoomTypeServiceInterface;

@RestController
@RequestMapping("/api/RoomType")
public class RoomTypeController {
	@Autowired
	RoomTypeServiceInterface roomTypeServiceInterface;
	@PostMapping("/post")
	public String  createRoomType(@RequestBody RoomTypeDto roomTypeDto) {
		return roomTypeServiceInterface.createRoomType(roomTypeDto);
	}
}
