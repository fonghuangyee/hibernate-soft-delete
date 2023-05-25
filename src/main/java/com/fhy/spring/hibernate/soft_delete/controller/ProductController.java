package com.fhy.spring.hibernate.soft_delete.controller;

import com.fhy.spring.hibernate.soft_delete.entity.Product;
import com.fhy.spring.hibernate.soft_delete.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<Product> findAll() {
        return productService.findAll();
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable("productId") long productId) {
        productService.delete(productId);
    }

}
