package com.fhy.spring.hibernate.soft_delete.controller;

import com.fhy.spring.hibernate.soft_delete.entity.Invoice;
import com.fhy.spring.hibernate.soft_delete.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping()
    public List<Invoice> findAll() {
        return invoiceService.findAll();
    }

    @DeleteMapping("/{invoiceId}")
    public void delete(@PathVariable("invoiceId") long invoiceId) {
        invoiceService.delete(invoiceId);
    }
}
