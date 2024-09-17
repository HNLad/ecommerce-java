package com.assign.ecommerce.service;

import java.util.List;

import com.assign.ecommerce.model.OrderModel;
import com.assign.ecommerce.model.UserModel;

public interface OrderService {

//	public OrderModel createOrder(OrderModel order);
//	
//	public List<OrderModel> findOrdersByUser(UserModel user);

	public OrderModel createOrder(OrderModel order);

	public List<OrderModel> findOrdersByUser(UserModel user);

	public OrderModel findOrderById(Long orderId, UserModel user);

}
