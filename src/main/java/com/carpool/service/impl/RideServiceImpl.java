package com.carpool.service.impl;

import com.carpool.dto.RideDto;
import com.carpool.entity.RideEntity;
import com.carpool.mapper.RideEntityDtoMapper;
import com.carpool.repository.RideRepository;
import com.carpool.service.HaversineService;
import com.carpool.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RideServiceImpl implements RideService{

    RideRepository rideRepository;
    RideEntityDtoMapper rideMapper;

    @Autowired
	HaversineService haversineService;

    @Autowired
    public RideServiceImpl(RideRepository rideRepository, RideEntityDtoMapper rideMapper) {
        this.rideRepository = rideRepository;
        this.rideMapper = rideMapper;
    }

    @Override
    public List<RideDto> search(double fromLat, double fromLon, double toLat, double toLon) {
		return rideRepository
				.findAll()
				.stream().filter(entity -> isOnWay(entity, fromLat, fromLon, toLat, toLon))
				.map(entity -> rideMapper.toDto(entity)).collect(Collectors.toList());
    }

	private boolean isOnWay(RideEntity entity, double fromLat, double fromLon, double toLat, double toLon) {
    	return haversineService.calculateDistance(
						entity.getFrom().getLatitude(),
						entity.getFrom().getLongtitude(),
						fromLat,
						fromLon) < 1 &&
				haversineService.calculateDistance(
						entity.getTo().getLatitude(),
						entity.getTo().getLongtitude(),
						toLat,
						toLon) < 1;
	}

	@Override
	public List<RideDto> findAll() {
		List<RideEntity> entities = rideRepository.findAll();
		return entities.stream().map(entity -> rideMapper.toDto(entity)).collect(Collectors.toList());
	}

	@Override
	public RideDto save(RideDto rideDto) throws Exception {
		Optional<RideEntity> entity = rideRepository.findById(rideDto.getId());
		if (entity.isPresent()) {
			throw new Exception("Subject already exists!");
		}

		RideEntity subject = rideRepository.save(rideMapper.toEntity(rideDto));
		return rideMapper.toDto(subject);
	}
}