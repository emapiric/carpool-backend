package com.carpool.repository;

import com.carpool.entity.UserEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path="user")
public interface UserRepository extends JpaRepository<UserEntity,Long> {
	List<UserEntity> findByEmail(String email);
	List<UserEntity> findByUsername(String username);
}
