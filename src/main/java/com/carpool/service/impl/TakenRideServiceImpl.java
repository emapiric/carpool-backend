package com.carpool.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpool.dto.TakenRideDto;
import com.carpool.entity.RideEntity;
import com.carpool.entity.TakenRideEntity;
import com.carpool.entity.UserEntity;
import com.carpool.mapper.RideEntitySimpleDtoMapper;
import com.carpool.mapper.TakenRideEntityDtoMapper;
import com.carpool.mapper.UserEntitySimpleDtoMapper;
import com.carpool.repository.RideRepository;
import com.carpool.repository.TakenRideRepository;
import com.carpool.repository.UserRepository;
import com.carpool.service.TakenRideService;

@Service
@Transactional
public class TakenRideServiceImpl implements TakenRideService {

	private TakenRideRepository takenRideRepository;
	private RideRepository rideRepository;
	private UserRepository userRepository;
	private TakenRideEntityDtoMapper takenRideMapper;

	@Autowired
	public TakenRideServiceImpl(TakenRideRepository takenRideRepository, TakenRideEntityDtoMapper takenRideMapper,
			RideRepository rideRepository, UserRepository userRepository) {
		super();
		this.takenRideRepository = takenRideRepository;
		this.takenRideMapper = takenRideMapper;
		this.rideRepository = rideRepository;
		this.userRepository = userRepository;
	}


	@Override
	public Optional<TakenRideDto> update(TakenRideDto takenRide) throws Exception {
		Optional<RideEntity> rideOptional = rideRepository.findById(takenRide.getId().getRideId());
		Optional<UserEntity> userOptional = userRepository.findById(takenRide.getId().getUserId());
		if (!rideOptional.isPresent()) {
			throw new Exception("Ride does not exist");
		}
		if (!userOptional.isPresent()) {
			throw new Exception("User does not exist");
		}
		List<TakenRideEntity> takenRideList = takenRideRepository.findByUserAndRide(userOptional.get(), rideOptional.get());
		if (takenRideList.size()>=0) {
			TakenRideEntity takenRideEntity = takenRideList.get(0);
			takenRideEntity.setApproved(true);
			takenRideRepository.save(takenRideEntity);
			return Optional.of(takenRideMapper.toDto(takenRideEntity));
		} else {
			return Optional.empty();
		}
	}


}
