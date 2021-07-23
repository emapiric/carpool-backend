package com.carpool.repository;

import com.carpool.entity.TakenRideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "taken-ride", path="taken-ride")
public interface TakenRideRepository extends JpaRepository<TakenRideEntity,Long> {
}
