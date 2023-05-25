package com.fhy.spring.hibernate.soft_delete.listener;

import com.fhy.spring.hibernate.soft_delete.entity.Order;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {

    private int counter = 0;

    @PrePersist
    @PreUpdate
    @PreRemove
    public void onUpdate(Order order) {
        log.info("invoke order listener: {}", ++counter);
        order.getProduct();
    }
}
