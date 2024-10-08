package com.zubak.demo.soap;

import com.zubak.demo.wsdl.InvoiceRequest;
import com.zubak.demo.wsdl.InvoiceResponse;
import jakarta.xml.bind.JAXBElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.namespace.QName;

@Component
public class SoapClient extends WebServiceGatewaySupport {

    public InvoiceResponse sendInvoice(InvoiceRequest invoiceRequest) {
        // Create JAXBElement for the request
        JAXBElement<InvoiceRequest> requestElement = new JAXBElement<>(
                new QName("http://example.com/invoices", "InvoiceRequest"),
                InvoiceRequest.class,
                invoiceRequest
        );

        // Send the request and receive the response as InvoiceResponse
        InvoiceResponse response = (InvoiceResponse) getWebServiceTemplate().marshalSendAndReceive(
                "http://localhost:8080/ws/invoices",requestElement, new SoapActionCallback("http://example.com/invoices"));

        return response;
    }
}
