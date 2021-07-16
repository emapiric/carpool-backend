package com.carpool.repository;

import com.carpool.entity.RideEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<RideEntity,Long> {
}
