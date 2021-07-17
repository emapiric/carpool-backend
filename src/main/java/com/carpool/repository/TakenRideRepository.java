package com.carpool.repository;

import com.carpool.entity.TakenRideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TakenRideRepository extends JpaRepository<TakenRideEntity,Long> {
}
