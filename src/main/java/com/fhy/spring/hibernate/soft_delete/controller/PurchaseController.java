package com.fhy.spring.hibernate.soft_delete.controller;

import com.fhy.spring.hibernate.soft_delete.entity.Purchase;
import com.fhy.spring.hibernate.soft_delete.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/{productId}")
    public Purchase create(@PathVariable("productId") long productId) {
        return purchaseService.purchase(productId);
    }

    @GetMapping()
    public List<Purchase> findAll() {
        return purchaseService.findAll();
    }
}
