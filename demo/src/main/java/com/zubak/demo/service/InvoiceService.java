package com.zubak.demo.service;

import com.zubak.demo.model.Invoice;
import com.example.invoices.InvoiceRequest;
import com.example.invoices.InvoiceResponse;
import com.zubak.demo.utils.LocalDateAdapter;
import org.springframework.stereotype.Service;
import com.zubak.demo.repository.InvoiceRepository;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    private final LocalDateAdapter localDateAdapter;

    public InvoiceService(InvoiceRepository invoiceRepository,
                          LocalDateAdapter localDateAdapter) {
        this.localDateAdapter = localDateAdapter;
        this.invoiceRepository = invoiceRepository;
    }

    public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) throws Exception {
        // Map DTO to entity
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(invoiceRequest.getInvoiceNumber());
        invoice.setAmount(invoiceRequest.getAmount());
        invoice.setCustomerName(invoiceRequest.getCustomerName());
        invoice.setIssueDate(localDateAdapter.unmarshal(invoiceRequest.getIssueDate()));
        invoice.setDueDate(localDateAdapter.unmarshal(invoiceRequest.getDueDate()));
        invoice.setTaxAmount(invoiceRequest.getTaxAmount());
        invoice.setStatus(invoiceRequest.getStatus());

        // Save to DB
        invoiceRepository.save(invoice);

        // Prepare response
        InvoiceResponse response = new InvoiceResponse();
        response.setInvoiceNumber(invoice.getInvoiceNumber());
        response.setStatus("Invoice created successfully");

        return response;
    }

}
