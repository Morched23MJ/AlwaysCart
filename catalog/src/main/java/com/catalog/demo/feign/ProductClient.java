package com.catalog.demo.feign;

import com.catalog.demo.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "inventory")
public interface ProductClient {
    @GetMapping("/api/products")
    List<Product> getProducts();

    @GetMapping("/api/products/{id}")
    Product getProduct(@PathVariable("id") Long id);
}
