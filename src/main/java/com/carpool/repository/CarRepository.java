package com.carpool.repository;

import com.carpool.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("car")
public interface CarRepository extends JpaRepository<CarEntity,Long> {
}
