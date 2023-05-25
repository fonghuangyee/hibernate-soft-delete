package com.fhy.spring.hibernate.soft_delete.service;

import com.fhy.spring.hibernate.soft_delete.entity.Invoice;
import com.fhy.spring.hibernate.soft_delete.entity.Product;
import com.fhy.spring.hibernate.soft_delete.repo.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepo invoiceRepo;

    public Invoice create(Product product) {
        Invoice invoice = new Invoice();
        invoice.setProduct(product);
        return invoiceRepo.save(invoice);
    }

    public List<Invoice> findAll() {
        List<Invoice> invoices = invoiceRepo.findAll();
        invoices.forEach(invoice -> {
            invoice.setViewCount(invoice.getViewCount() + 1);
            invoiceRepo.save(invoice);
        });
        return invoices;
    }

    public void delete(long id) {
        invoiceRepo.deleteById(id);
    }
}
