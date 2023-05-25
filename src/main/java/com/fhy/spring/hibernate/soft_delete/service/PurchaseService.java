package com.fhy.spring.hibernate.soft_delete.service;

import com.fhy.spring.hibernate.soft_delete.entity.Invoice;
import com.fhy.spring.hibernate.soft_delete.entity.Payment;
import com.fhy.spring.hibernate.soft_delete.entity.Product;
import com.fhy.spring.hibernate.soft_delete.entity.Purchase;
import com.fhy.spring.hibernate.soft_delete.repo.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private ProductService productService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PurchaseRepo purchaseRepo;

    public Purchase purchase(long productId) {
        Product product = productService.findById(productId);
        Invoice invoice = invoiceService.create(product);
        Payment payment = paymentService.create(product);
        Purchase purchase = new Purchase();
        purchase.setProduct(product);
        purchase.setInvoice(invoice);
        purchase.setPayment(payment);
        return purchaseRepo.save(purchase);
    }

    public List<Purchase> findAll() {
        return purchaseRepo.findAll();
    }
}
