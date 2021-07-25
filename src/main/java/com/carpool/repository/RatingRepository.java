package com.carpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.carpool.entity.RatingEntity;

@RepositoryRestResource(collectionResourceRel = "rating", path="rating")
public interface RatingRepository extends JpaRepository<RatingEntity,Long> {
}
