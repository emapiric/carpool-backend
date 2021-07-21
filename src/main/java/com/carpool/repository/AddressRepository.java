package com.carpool.repository;

import com.carpool.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.xml.xpath.XPath;

//@RepositoryRestResource(collectionResourceRel = "address", path="address")
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
