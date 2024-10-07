package com.zubak.demo.service;

import com.zubak.demo.model.Invoice;
import com.zubak.demo.model.InvoiceRequest;
import com.zubak.demo.model.InvoiceResponse;
import org.springframework.stereotype.Service;
import com.zubak.demo.repository.InvoiceRepository;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) {
        // Map DTO to entity
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(invoiceRequest.getInvoiceNumber());
        invoice.setAmount(invoiceRequest.getAmount());
        invoice.setCustomerName(invoiceRequest.getCustomerName());
        invoice.setIssueDate(invoiceRequest.getIssueDate());
        invoice.setDueDate(invoiceRequest.getDueDate());
        invoice.setTaxAmount(invoiceRequest.getTaxAmount());
        invoice.setStatus(invoiceRequest.getStatus());

        // Save to DB
//        invoiceRepository.save(invoice);

        // Prepare response
        InvoiceResponse response = new InvoiceResponse();
        response.setInvoiceNumber(invoice.getInvoiceNumber());
        response.setStatus("Invoice created successfully");

        return response;
    }

}
