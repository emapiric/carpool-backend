package com.carpool.service.impl;

import com.carpool.dto.RideDto;
import com.carpool.entity.RideEntity;
import com.carpool.mapper.RideEntityDtoMapper;
import com.carpool.repository.RideRepository;
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
    public RideServiceImpl(RideRepository rideRepository, RideEntityDtoMapper rideMapper) {
        this.rideRepository = rideRepository;
        this.rideMapper = rideMapper;
    }

    @Override
    public List<RideDto> search(double fromLatitude, double fromLongtitude, double toLatitude, double toLongtitude) {
		return rideRepository
				.findAll()
				.stream().filter(entity -> isOnWay(entity, fromLatitude, fromLongtitude, toLatitude, toLongtitude))
				.map(entity -> rideMapper.toDto(entity)).collect(Collectors.toList());
    }

	private boolean isOnWay(RideEntity entity, double fromLatitude, double fromLongtitude, double toLatitude, double toLongtitude) {
    	return isNearby(entity.getFrom().getLatitude(),entity.getFrom().getLongtitude(),fromLatitude,fromLongtitude) &&
				isNearby(entity.getTo().getLatitude(),entity.getTo().getLongtitude(),toLatitude,toLongtitude);
	}

	private boolean isNearby(double rideLatitude, double rideLongtitude, double searchedLatitude, double searchedLongtitude) {
		return true;
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