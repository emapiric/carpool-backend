package com.carpool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.carpool.entity.RideEntity;
import com.carpool.entity.TakenRideEntity;
import com.carpool.entity.UserEntity;

@RepositoryRestResource(collectionResourceRel = "takenRide", path="takenRides")
public interface TakenRideRepository extends JpaRepository<TakenRideEntity,Long> {

	List<TakenRideEntity> findByUserAndRide(UserEntity user, RideEntity ride);
}
