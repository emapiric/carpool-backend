package com.carpool.controller.rest;

import com.carpool.dto.MyDto;
import com.carpool.dto.RideDto;
import com.carpool.dto.RideRequestDto;
import com.carpool.dto.UserDto;
import com.carpool.service.RideService;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/ride")
public class RideController implements MyDto {

    private static final long serialVersionUID = 1L;
    RideService rideService;

    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

	@RequestMapping("search")
	public ResponseEntity<List<RideDto>> search(RideRequestDto rideRequestDto)  {
		return ResponseEntity.status(HttpStatus.OK).body(rideService.search(rideRequestDto));
	}

    @GetMapping
    public ResponseEntity<List<RideDto>> findAll(){
    	return ResponseEntity.status(HttpStatus.OK).body(rideService.findAll());
    }

	@GetMapping("findUpcomingByUserId/{userId}")
	public ResponseEntity<List<RideDto>> findUpcomingByUserId(@PathVariable Long userId){
		return ResponseEntity.status(HttpStatus.OK).body(rideService.findUpcomingByUserId(userId));
	}
    
    @PostMapping
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody RideDto rideDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			bindingResult.getAllErrors().forEach((error) -> {
				String fieldName = ((FieldError) error).getField();
				String errorMessage = error.getDefaultMessage();
				errors.put(fieldName, errorMessage);
			});
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving subject " + errors);
		} else {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(rideService.save(rideDto));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
	}

	@DeleteMapping("{id}/delete")
	public ResponseEntity<Object> deleteRide(@PathVariable int id){
		try {
			rideService.deleteRide(id);
			//@Todo notify users
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ride not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Ride successfully deleted");
	}

}
