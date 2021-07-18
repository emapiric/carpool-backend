package com.carpool.service;

import com.carpool.dto.RideDto;

import java.util.List;

import javax.validation.Valid;

public interface RideService {

    List<RideDto> search();
    List<RideDto> findAll();
	RideDto save(RideDto rideDto) throws Exception;
}
