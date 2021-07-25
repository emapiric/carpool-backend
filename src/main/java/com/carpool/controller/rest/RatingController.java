package com.carpool.controller.rest;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.carpool.dto.RatingDto;
import com.carpool.service.RatingService;


@RestController
@RequestMapping("api/rating")
public class RatingController {
	
	private RatingService ratingService;
	
	@Autowired
	public RatingController(RatingService ratingService) {
		super();
	}
	
	
	 @PostMapping
		public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody RatingDto ratingDto,
				BindingResult bindingResult) {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = new HashMap<>();
				bindingResult.getAllErrors().forEach((error) -> {
					String fieldName = ((FieldError) error).getField();
					String errorMessage = error.getDefaultMessage();
					errors.put(fieldName, errorMessage);
				});
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving rating " + errors);
			} else {
				try {
					return ResponseEntity.status(HttpStatus.OK).body(ratingService.save(ratingDto));
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
				}
			}
		}	

}
