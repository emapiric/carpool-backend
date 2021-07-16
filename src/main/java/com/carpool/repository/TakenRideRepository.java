package com.carpool.repository;

import com.carpool.entity.TakenRideEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakenRideRepository extends JpaRepository<TakenRideEntity,Long> {
}
