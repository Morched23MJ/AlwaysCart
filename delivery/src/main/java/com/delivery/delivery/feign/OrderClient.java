package com.delivery.delivery.feign;

import com.delivery.delivery.entities.Delivery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "order")
public interface OrderClient {

    @PostMapping("/api/confirm")
    Object confirmOrder(@RequestBody Delivery delivery);

    @PostMapping("/api/pend")
    Object pendOrder(@RequestBody Delivery delivery);

    @GetMapping("/api/orders")
    List<Object> getOrders();
}
