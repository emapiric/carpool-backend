package com.carpool.repository;

import com.carpool.entity.WorkingTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "working-time", path="working-time")
public interface WorkingTimeRepository extends JpaRepository<WorkingTimeEntity,Long> {
}
