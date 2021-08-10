package com.carpool.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.carpool.dto.RideDto;
import com.carpool.mapper.TakenRideEntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpool.dto.TakenRideDto;
import com.carpool.entity.RideEntity;
import com.carpool.entity.TakenRideEntity;
import com.carpool.entity.UserEntity;
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

    @Autowired
    public TakenRideServiceImpl(TakenRideRepository takenRideRepository, RideRepository rideRepository,
                                UserRepository userRepository) {
        super();
        this.takenRideRepository = takenRideRepository;
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<TakenRideDto> update(TakenRideDto takenRide) throws Exception {
        Optional<RideEntity> rideOptional = rideRepository.findById(takenRide.getId().getRideId());
        Optional<UserEntity> userOptional = userRepository.findById(takenRide.getId().getUserId());
        List<TakenRideEntity> takenRideList = takenRideRepository.findByUserAndRide(userOptional.get(),
                rideOptional.get());
        if (takenRideList.size() >= 0) {
            System.out.println(userOptional.get().getTakenRides().get(0));
            for (TakenRideEntity takenRideEntityIt : userOptional.get().getTakenRides()) {
                if (takenRideEntityIt.getId().getRideId() == takenRide.getId().getRideId()
                        && takenRideEntityIt.getId().getUserId() == takenRide.getId().getUserId()) {
                    takenRideEntityIt.setApproved(takenRide.isApproved());
                    takenRideEntityIt.setDone(takenRide.isDone());
                }
            }
            userRepository.save(userOptional.get());
            return Optional.of(takenRide);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Long> findFutureApprovedByUserId(Long userId) {
        return takenRideRepository
                .findAll()
                .stream().filter(ride -> ride.getUser().getId() == userId &&
                        ride.isApproved() && !ride.isDone())
                .map(ride -> ride.getRide().getId()).collect(Collectors.toList());
    }

}
