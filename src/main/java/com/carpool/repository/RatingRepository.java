package com.carpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carpool.entity.RatingEntity;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity,Long> {
}
