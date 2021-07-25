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
	
//	@PostMapping
//	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody TakenRideDto takenRideDto, BindingResult bindingResult) {
//		if (bindingResult.hasErrors()) {
//			Map<String, String> errors = new HashMap<>();
//			bindingResult.getAllErrors().forEach((error) -> {
//				String fieldName = ((FieldError) error).getField();
//				String errorMessage = error.getDefaultMessage();
//				errors.put(fieldName, errorMessage);
//			});
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving taken ride " + errors);
//		} else {
//			try {
//				return ResponseEntity.status(HttpStatus.OK).body(takenRideService.save(takenRideDto));
//			} catch (Exception e) {
//				e.printStackTrace();
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//			}
//		}
//	}
	
	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@RequestBody TakenRideDto takenRideDto) {
		try {
			Optional<TakenRideDto> exam = takenRideService.update(takenRideDto);
			if (exam.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(takenRideDto);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(takenRideDto);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}
	
//	@DeleteMapping
//	public @ResponseBody ResponseEntity<Object> delete(@RequestBody TakenRideId takenRideId) {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(takenRideService.delete(takenRideId));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//
//	}

}
