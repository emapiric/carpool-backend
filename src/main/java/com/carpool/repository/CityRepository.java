package com.carpool.repository;

import com.carpool.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "city", path="city")
public interface CityRepository extends JpaRepository<CityEntity,Long> {
}
