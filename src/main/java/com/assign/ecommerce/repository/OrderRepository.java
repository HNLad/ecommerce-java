package com.assign.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assign.ecommerce.model.OrderModel;
import com.assign.ecommerce.model.UserModel;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {

	List<OrderModel> findByUser(UserModel user);
	
	Optional<OrderModel> findById(Long orderId);
}
