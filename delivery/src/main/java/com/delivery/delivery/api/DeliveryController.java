package com.delivery.delivery.api;

import com.delivery.delivery.dao.DeliveryRepository;
import com.delivery.delivery.entities.Delivery;
import com.delivery.delivery.feign.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RestResource
@RequestMapping("api")
public class DeliveryController {

    @Autowired
    OrderClient orderClient;

    @Autowired
    DeliveryRepository deliveryRepository;

    @PostMapping("/delivery/{id}")
    Delivery saveDelivery(@PathVariable("id") Long idOrder, @RequestBody Delivery delivery) {
        Object orderConfirmation = orderClient.confirmOrder(delivery);
        if (orderConfirmation != null) {
            return deliveryRepository.save(delivery);
        } else {
            return null;
        }
    }

    @GetMapping("/delivery/orders")
    List<Object> getOrders() { return orderClient.getOrders(); }
}
