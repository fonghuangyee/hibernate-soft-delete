package com.fhy.spring.hibernate.soft_delete.service;

import com.fhy.spring.hibernate.soft_delete.entity.Product;
import com.fhy.spring.hibernate.soft_delete.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product create(String name) {
        Product product = new Product();
        product.setName(name);
        return productRepo.save(product);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(long id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void delete(long id) {
        productRepo.deleteById(id);
    }
}
