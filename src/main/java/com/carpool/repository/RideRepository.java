package com.carpool.repository;

import com.carpool.entity.RideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:8100")
@RepositoryRestResource(collectionResourceRel = "ride", path="rides")
public interface RideRepository extends JpaRepository<RideEntity,Long> {
}
