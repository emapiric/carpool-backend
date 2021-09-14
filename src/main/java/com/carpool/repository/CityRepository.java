package com.carpool.repository;

import com.carpool.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("city")
public interface CityRepository extends JpaRepository<CityEntity,Long> {
}
