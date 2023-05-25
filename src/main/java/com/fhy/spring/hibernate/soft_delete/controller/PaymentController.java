package com.fhy.spring.hibernate.soft_delete.controller;

import com.fhy.spring.hibernate.soft_delete.entity.Payment;
import com.fhy.spring.hibernate.soft_delete.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping()
    public List<Payment> findAll() {
        return paymentService.findAll();
    }

    @DeleteMapping("/{paymentId}")
    public void delete(@PathVariable("paymentId") long paymentId) {
        paymentService.delete(paymentId);
    }
}
