package com.carpool.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.carpool.service.NotificationService;
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
    private NotificationService notificationService;

    @Autowired
    public TakenRideServiceImpl(TakenRideRepository takenRideRepository, RideRepository rideRepository,
                                UserRepository userRepository, NotificationService notificationService) {
        super();
        this.takenRideRepository = takenRideRepository;
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
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
                    notificationService.addRideApproved(takenRideEntityIt);
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
                        ride.isApproved() && (ride.getRide().getDateTime().isEqual(LocalDateTime.now()) || ride.getRide().getDateTime().isAfter(LocalDateTime.now())))
                .map(ride -> ride.getRide().getId()).collect(Collectors.toList());
    }

    @Override
    public List<TakenRideEntity> findAllByRide(Long rideId) {
        return takenRideRepository
                .findAll()
                .stream().filter(ride -> ride.getId().getRideId() == rideId).collect(Collectors.toList());
    }

    @Override
    public TakenRideEntity findByUserAndRide(UserEntity user, RideEntity ride) {
        return takenRideRepository.findByUserAndRide(user, ride).get(0);
    }

}
