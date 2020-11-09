package com.catalog.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class CatalogApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }

}
