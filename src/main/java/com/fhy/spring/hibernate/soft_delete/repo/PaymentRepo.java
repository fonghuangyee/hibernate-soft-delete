package com.fhy.spring.hibernate.soft_delete.repo;

import com.fhy.spring.hibernate.soft_delete.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
}