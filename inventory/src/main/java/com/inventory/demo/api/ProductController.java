package com.inventory.demo.api;

import com.inventory.demo.dao.ProductRepository;
import com.inventory.demo.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        try {
            return productRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product update) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(update.getName());
                    product.setBrand(update.getBrand());
                    product.setSupplier(update.getSupplier());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    return productRepository.save(update);
                });
    }

    @DeleteMapping("/products/{id}")
    public void saveProduct(@PathVariable("id") Long id) {
        try {
            Product product = productRepository.findById(id).get();
            productRepository.delete(product);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
