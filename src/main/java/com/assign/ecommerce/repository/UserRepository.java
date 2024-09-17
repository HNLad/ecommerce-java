package com.assign.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assign.ecommerce.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	
	Optional<UserModel> findByUsername(String username);
	
}
