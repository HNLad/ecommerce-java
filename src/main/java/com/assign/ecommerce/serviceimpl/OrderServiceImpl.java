package com.assign.ecommerce.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assign.ecommerce.exception.InsufficientStockException;
import com.assign.ecommerce.exception.OrderNotFoundException;
import com.assign.ecommerce.exception.ProductNotFoundException;
import com.assign.ecommerce.model.OrderItemModel;
import com.assign.ecommerce.model.OrderModel;
import com.assign.ecommerce.model.ProductModel;
import com.assign.ecommerce.model.UserModel;
import com.assign.ecommerce.repository.OrderRepository;
import com.assign.ecommerce.repository.ProductRepository;
import com.assign.ecommerce.service.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public OrderModel createOrder(OrderModel order) {
        validateOrder(order);
        order.calculateTotalPrice();

        for (OrderItemModel item : order.getOrderItems()) {
        	item.setOrder(order);
            ProductModel product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            if (product.getStock() < item.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }

        return orderRepository.save(order);
    }

    @Override
    public List<OrderModel> findOrdersByUser(UserModel user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public OrderModel findOrderById(Long orderId, UserModel user) {
        return orderRepository.findById(orderId)
                .filter(order -> order.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new OrderNotFoundException("Order not found or does not belong to the user"));
    }

    private void validateOrder(OrderModel order) {
        if (order == null || order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            throw new IllegalArgumentException("Order or order items cannot be null or empty");
        }
    }
}
