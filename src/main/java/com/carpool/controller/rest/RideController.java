package com.carpool.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carpool.dto.RideDto;
import com.carpool.dto.RideRequestDto;
import com.carpool.service.RideService;

@RestController
@RequestMapping("/api/ride")
public class RideController {

	private RideService rideService;

	@Autowired
	public RideController(RideService rideService) {
		this.rideService = rideService;
	}

	@RequestMapping("search")
	public ResponseEntity<List<RideDto>> search(RideRequestDto rideRequestDto) {
		return ResponseEntity.status(HttpStatus.OK).body(rideService.search(rideRequestDto));
	}

	@GetMapping
	public ResponseEntity<List<RideDto>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(rideService.findAll());
	}

	@GetMapping("findUpcomingByUserId/{userId}")
	public ResponseEntity<List<RideDto>> findUpcomingByUserId(@PathVariable Long userId) {
		return ResponseEntity.status(HttpStatus.OK).body(rideService.findUpcomingByUserId(userId));
	}

//	@PostMapping
//	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody RideDto rideDto, BindingResult bindingResult) {
//		if (bindingResult.hasErrors()) {
//			Map<String, String> errors = new HashMap<>();
//			bindingResult.getAllErrors().forEach((error) -> {
//				String fieldName = ((FieldError) error).getField();
//				String errorMessage = error.getDefaultMessage();
//				errors.put(fieldName, errorMessage);
//			});
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving ride " + errors);
//		} else {
//			try {
//				return ResponseEntity.status(HttpStatus.OK).body(rideService.save(rideDto));
//			} catch (Exception e) {
//				e.printStackTrace();
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//			}
//		}
//	}

	@DeleteMapping("{id}/delete")
	public ResponseEntity<Object> deleteRide(@PathVariable int id) {
		try {
			rideService.deleteRide(id);
			// @Todo notify users
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ride not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Ride successfully deleted");
	}

}
