package com.carpool.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpool.dto.RideDto;
import com.carpool.dto.RideRequestDto;
import com.carpool.entity.RideEntity;
import com.carpool.mapper.RideEntityDtoMapper;
import com.carpool.repository.RideRepository;
import com.carpool.service.HaversineService;
import com.carpool.service.RideService;

@Service
@Transactional
public class RideServiceImpl implements RideService {

    private RideRepository rideRepository;
    private RideEntityDtoMapper rideMapper;
    private HaversineService haversineService;

    @Autowired
    public RideServiceImpl(RideRepository rideRepository, RideEntityDtoMapper rideMapper, HaversineService haversineService) {
        this.rideRepository = rideRepository;
        this.rideMapper = rideMapper;
        this.haversineService = haversineService;
    }

    @Override
    public List<RideDto> search(RideRequestDto rideRequest) {
        return rideRepository
                .findAll()
                .stream().filter(ride -> isOnWay(ride, rideRequest) &&
                        isLaterToday(ride.getDateTime(), rideRequest.getDateTime()) &&
                        ride.hasSpace())
                .map(ride -> rideMapper.toDto(ride)).collect(Collectors.toList());
    }

    private boolean isOnWay(RideEntity ride, RideRequestDto rideRequest) {
        return haversineService.calculateDistance(
                ride.getFrom().getLatitude(),
                ride.getFrom().getLongtitude(),
                rideRequest.getFrom().getLatitude(),
                rideRequest.getFrom().getLongtitude()) < 1 &&
                haversineService.calculateDistance(
                        ride.getTo().getLatitude(),
                        ride.getTo().getLongtitude(),
                        rideRequest.getTo().getLatitude(),
                        rideRequest.getTo().getLongtitude()) < 1;
    }

    private boolean isLaterToday(LocalDateTime rideTime, LocalDateTime rideRequestTime) {
        return rideTime.getYear() == rideRequestTime.getYear() &&
                rideTime.getMonth() == rideRequestTime.getMonth() &&
                rideTime.getDayOfMonth() == rideRequestTime.getDayOfMonth() &&
                (rideTime.isAfter(rideRequestTime) || rideTime.isEqual(rideRequestTime));
    }

    @Override
    public List<RideDto> findAll() {
        List<RideEntity> entities = rideRepository.findAll();
        return entities.stream().map(entity -> rideMapper.toDto(entity)).collect(Collectors.toList());
    }

    @Override
    public List<RideDto> findUpcomingByUserId(Long userId) {
        return rideRepository
                .findAll()
                .stream().filter(ride -> ride.getDriver().getId() == userId &&
                        ride.getDateTime().isAfter(LocalDateTime.now()))
                .map(ride -> rideMapper.toDto(ride)).collect(Collectors.toList());
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

    @Override
    public void deleteRide(int id) throws Exception {
        rideRepository.deleteById((long) id);
    }

}