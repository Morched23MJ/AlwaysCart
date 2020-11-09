package com.order.demo.api;

import com.order.demo.dao.OrderRepository;
import com.order.demo.entities.Order;
import com.order.demo.feign.ProductClient;
import com.order.demo.models.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductClient productClient;

    @PostMapping("/confirm")
    public Object confirmOrder(@RequestBody Delivery delivery) {
        Optional<Order> order = orderRepository.findById(delivery.getIdOrder());
        System.out.println("Confirming...");
        if (order.get() != null) {
            order.get().setStatus("confirmed");
            orderRepository.save(order.get());
            return order.get();
        } else {
            return null;
        }
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @PostMapping("/orders")
    public Order saveOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }
}
