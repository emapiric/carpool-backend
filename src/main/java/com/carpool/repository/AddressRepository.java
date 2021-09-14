package com.carpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carpool.entity.AddressEntity;
import org.springframework.stereotype.Repository;

@Repository("address")
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
