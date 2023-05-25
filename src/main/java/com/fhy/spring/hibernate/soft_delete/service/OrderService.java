package com.fhy.spring.hibernate.soft_delete.service;

import com.fhy.spring.hibernate.soft_delete.entity.Order;
import com.fhy.spring.hibernate.soft_delete.entity.Product;
import com.fhy.spring.hibernate.soft_delete.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductService productService;

    public Order create(long productId) {
        Product product = productService.findById(productId);
        Order order = new Order();
        order.setProduct(product);
        return orderRepo.save(order);
    }

    public List<Order> findAll() {
        List<Order> orders = orderRepo.findAll();
        orders.forEach(order -> {
            order.setViewCount(order.getViewCount() + 1);
            orderRepo.save(order);
        });
        return orders;
    }

    public void delete(long id) {
        orderRepo.deleteById(id);
    }
}
