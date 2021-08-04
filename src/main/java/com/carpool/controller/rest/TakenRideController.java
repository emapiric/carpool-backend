package com.carpool.controller.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.carpool.dto.TakenRideDto;
import com.carpool.service.TakenRideService;


@RestController
@RequestMapping("/api/takenRide")
public class TakenRideController {
	private TakenRideService takenRideService;

	@Autowired
	public TakenRideController(TakenRideService takenRideService) {
		super();
		this.takenRideService = takenRideService;
	}
	
	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@RequestBody TakenRideDto takenRideDto) {
		try {
			Optional<TakenRideDto> takenRide = takenRideService.update(takenRideDto);
			if (takenRide.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(takenRideDto);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(takenRideDto);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}

}
