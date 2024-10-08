package com.zubak.demo.controller;

import com.example.invoices.InvoiceRequest;
import com.example.invoices.InvoiceResponse;
import com.zubak.demo.service.InvoiceService;
import jakarta.xml.bind.JAXBElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.namespace.QName;

/**
 * This class is used for processing incoming SOAP requests
 */
@Endpoint
public class InvoiceEndpoint {

    private final Logger log = LoggerFactory.getLogger(InvoiceEndpoint.class);

    private static final String NAMESPACE_URI = "http://example.com/invoices";
    private final InvoiceService invoiceService;

    public InvoiceEndpoint(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "InvoiceRequest")
    @ResponsePayload
    public JAXBElement<InvoiceResponse> handleInvoice(@RequestPayload JAXBElement<InvoiceRequest> request) {
        // Extract the InvoiceRequest from the JAXBElement
        InvoiceRequest invoiceRequest = request.getValue();

        // Call the service with the InvoiceRequest
        log.info("Received request: {}", request);
        InvoiceResponse response = null;
        try {
            response = invoiceService.createInvoice(invoiceRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Create JAXBElement for the response
        log.info("Sending response: {}", response);
        return new JAXBElement<>(new QName(NAMESPACE_URI, "InvoiceResponse"), InvoiceResponse.class, response);
    }
}
