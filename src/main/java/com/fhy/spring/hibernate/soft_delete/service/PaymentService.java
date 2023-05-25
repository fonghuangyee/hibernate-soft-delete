package com.fhy.spring.hibernate.soft_delete.service;

import com.fhy.spring.hibernate.soft_delete.entity.Payment;
import com.fhy.spring.hibernate.soft_delete.entity.Product;
import com.fhy.spring.hibernate.soft_delete.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    public Payment create(Product product) {
        Payment payment = new Payment();
        payment.setProduct(product);
        return paymentRepo.save(payment);
    }

    public List<Payment> findAll() {
        List<Payment> payments = paymentRepo.findAll();
        payments.forEach(payment -> {
            payment.setViewCount(payment.getViewCount() + 1);
            paymentRepo.save(payment);
        });
        return payments;
    }

    public void delete(long id) {
        paymentRepo.deleteById(id);
    }
}
