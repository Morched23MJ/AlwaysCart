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

    @PostMapping("/deliveries")
    Delivery saveDelivery(@RequestBody Delivery delivery) {
        Object orderConfirmation = orderClient.confirmOrder(delivery);
        if (orderConfirmation != null) {
            return deliveryRepository.save(delivery);
        } else {
            return null;
        }
    }

    @GetMapping("/deliveries")
    List<Delivery> getDeliveries() { return deliveryRepository.findAll(); }

    @GetMapping("/deliveries/{id}")
    Delivery getDeliveries(@PathVariable("id") Long id) { return deliveryRepository.findById(id).get(); }

    @PutMapping("/deliveries/{id}")
    public Delivery updateDelivery(@PathVariable("id") Long id, @RequestBody Delivery update) {
        return deliveryRepository.findById(id)
                .map(delivery -> {
                    delivery.setAddress(update.getAddress());
                    orderClient.pendOrder(delivery);
                    delivery.setIdOrder(update.getIdOrder());
                    orderClient.confirmOrder(delivery);
                    return deliveryRepository.save(delivery);
                })
                .orElseGet(() -> {
                    orderClient.confirmOrder(update);
                    return deliveryRepository.save(update);
                });
    }

    @DeleteMapping("/deliveries/{id}")
    public String deleteDelivery(@PathVariable("id") Long id) {
        try {
            Delivery delivery = deliveryRepository.findById(id).get();
            orderClient.pendOrder(delivery);
            deliveryRepository.delete(delivery);
            return "Delivery deleted successfully.";
        } catch (Exception e) {
            return "Delivery deletion failed.";
        }
    }

    @GetMapping("/delivery/orders")
    List<Object> getOrders() { return orderClient.getOrders(); }
}
