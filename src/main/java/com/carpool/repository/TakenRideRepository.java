package com.carpool.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.carpool.entity.RideEntity;
import com.carpool.entity.TakenRideEntity;
import com.carpool.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository("takenRide")
public interface TakenRideRepository extends JpaRepository<TakenRideEntity,Long> {

	List<TakenRideEntity> findByUserAndRide(UserEntity user, RideEntity ride);
}
