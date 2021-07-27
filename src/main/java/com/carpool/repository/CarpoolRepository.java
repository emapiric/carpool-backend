package com.carpool.repository;

import com.carpool.entity.CarpoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "carpool", path="carpool")
public interface CarpoolRepository extends JpaRepository<CarpoolEntity,Long> {

}
