package com.catalog.demo.api;

import com.catalog.demo.feign.ProductClient;
import com.catalog.demo.models.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api")
public class ProductController {

    @Autowired
    ProductClient productClient;

    @GetMapping("/products")
    @HystrixCommand(fallbackMethod = "getFallbackProducts")
    public List<Product> getProducts() {
        return productClient.getProducts();
    }

    public List<Product> getFallbackProducts() {
        return new ArrayList<>();
    }

    @GetMapping("/products/{id}")
    @HystrixCommand(fallbackMethod = "getFallbackProduct")
    public Product getProduct(@PathVariable("id") Long id) {
        return productClient.getProduct(id);
    }

    public Product getFallbackProduct(@PathVariable("id") Long id) {
        return new Product(null, null, null, null);
    }
}
