package com.carpool.controller.rest;

import com.carpool.dto.MyDto;
import com.carpool.dto.RideDto;
import com.carpool.service.RideService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search/{fromLatitude}/{fromLongtitude}/{toLatitude}/{toLongtitude}")
    public ResponseEntity<List<RideDto>> search(@PathVariable double fromLatitude, @PathVariable double fromLongtitude,@PathVariable double toLatitude, @PathVariable double toLongtitude) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(rideService.search(fromLatitude,fromLongtitude, toLatitude,toLongtitude));
    }
    
    @GetMapping
    public ResponseEntity<List<RideDto>> findAll(){
    	return ResponseEntity.status(HttpStatus.OK).body(rideService.findAll());
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
}
