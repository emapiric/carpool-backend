package com.carpool.repository;

import com.carpool.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "car", path="car")
public interface CarRepository extends JpaRepository<CarEntity,Long> {
}
