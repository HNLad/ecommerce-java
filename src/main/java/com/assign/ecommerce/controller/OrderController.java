package com.assign.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assign.ecommerce.model.OrderModel;
import com.assign.ecommerce.model.UserModel;
import com.assign.ecommerce.repository.UserRepository;
import com.assign.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderModel order, Authentication authentication) {
        String username = authentication.getName();

        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        order.setUser(user);

        OrderModel createdOrder = orderService.createOrder(order);

        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/getAllOrdersForUser")
    public ResponseEntity<List<OrderModel>> getAllOrdersForUser(Authentication authentication) {
        String username = authentication.getName();
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<OrderModel> orders = orderService.findOrdersByUser(user);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable Long orderId, Authentication authentication) {
        String username = authentication.getName();
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        OrderModel order = orderService.findOrderById(orderId, user);
        return ResponseEntity.ok(order);
    }
}
