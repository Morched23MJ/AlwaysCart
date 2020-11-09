package com.order.demo.api;

import com.order.demo.dao.OrderRepository;
import com.order.demo.entities.Order;
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

    @PostMapping("/confirm")
    public Object confirmOrder(@RequestBody Delivery delivery) {
        Order order = orderRepository.findById(delivery.getIdOrder()).get();
        System.out.println("Confirming...");
        if (order != null) {
            order.setStatus("confirmed");
            orderRepository.save(order);
            return order;
        } else {
            return null;
        }
    }

    @PostMapping("/pend")
    public Object pendOrder(@RequestBody Delivery delivery) {
        Order order = orderRepository.findById(delivery.getIdOrder()).get();
        System.out.println("Pending...");
        if (order != null) {
            order.setStatus("pending");
            orderRepository.save(order);
            return order;
        } else {
            return null;
        }
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable("id") Long id) {
        return orderRepository.findById(id).get();
    }

    @PostMapping("/orders")
    public Order saveOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PutMapping("/orders/{id}")
    public Order updateProduct(@PathVariable("id") Long id, @RequestBody Order update) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(update.getStatus());
                    order.setItems(update.getItems());
                    return orderRepository.save(order);
                })
                .orElseGet(() -> {
                    return orderRepository.save(update);
                });
    }

    @DeleteMapping("/orders/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        try {
            Order order = orderRepository.findById(id).get();
            orderRepository.delete(order);
            return "Order deleted successfully.";
        } catch (Exception e) {
            return "Order deletion failed.";
        }
    }
}
