package com.carpool.repository;

import com.carpool.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity,Long> {
}
