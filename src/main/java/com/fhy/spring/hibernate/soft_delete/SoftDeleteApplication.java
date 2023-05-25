package com.fhy.spring.hibernate.soft_delete;

import com.fhy.spring.hibernate.soft_delete.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SoftDeleteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftDeleteApplication.class, args);
    }

    @Autowired
    private ProductService productService;

    @Bean
    public CommandLineRunner bootstrap() {
        return (args) -> productService.create("Product A");
    }
}
