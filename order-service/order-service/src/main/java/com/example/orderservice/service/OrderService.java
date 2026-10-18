
package com.example.orderservice.service;

import com.example.orderservice.dto.Product;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestClient restClient;

    public OrderService(OrderRepository orderRepository,
                        RestClient restClient) {
        this.orderRepository = orderRepository;
        this.restClient = restClient;
    }

    public Order createOrder(Order order) {

        Product product = restClient.get()
                .uri("http://localhost:8081/products/"
                        + order.getProductId())
                .retrieve()
                .body(Product.class);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }
}

