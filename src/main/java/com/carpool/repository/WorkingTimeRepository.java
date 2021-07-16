package com.carpool.repository;

import com.carpool.entity.WorkingTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingTimeRepository extends JpaRepository<WorkingTimeEntity,Long> {
}
