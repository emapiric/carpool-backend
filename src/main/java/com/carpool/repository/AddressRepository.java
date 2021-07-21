package com.carpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carpool.entity.AddressEntity;

//@RepositoryRestResource(collectionResourceRel = "address", path="address")
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
