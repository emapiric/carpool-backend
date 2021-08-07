package com.carpool.repository;

import com.carpool.entity.UserEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path="user")
public interface UserRepository extends JpaRepository<UserEntity,Long> {
	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByUsername(String username);
	Optional<UserEntity> findByConfirmationToken(String confirmationToken);
	List<Optional<UserEntity>> findByResetPasswordToken(String confirmationToken);
}
