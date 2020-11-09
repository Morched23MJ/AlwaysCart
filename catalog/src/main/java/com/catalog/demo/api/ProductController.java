package com.catalog.demo.api;

import com.catalog.demo.feign.ProductClient;
import com.catalog.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("api")
public class ProductController {

    @Autowired
    ProductClient productClient;

    @GetMapping("/products")
    public Collection<Product> getProducts() {
        return productClient.getProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return productClient.getProduct(id);
    }
}
