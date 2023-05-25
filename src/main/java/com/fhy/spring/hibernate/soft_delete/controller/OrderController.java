package com.fhy.spring.hibernate.soft_delete.controller;

import com.fhy.spring.hibernate.soft_delete.entity.Order;
import com.fhy.spring.hibernate.soft_delete.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{productId}")
    public Order create(@PathVariable("productId") long productId) {
        return orderService.create(productId);
    }

    @GetMapping()
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable("orderId") long orderId) {
        orderService.delete(orderId);
    }
}
