package com.zubak.demo.controller;

import com.zubak.demo.wsdl.InvoiceRequest;
import com.zubak.demo.wsdl.InvoiceResponse;
import com.zubak.demo.soap.SoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceClientController {

    private final SoapClient soapClient;

    public InvoiceClientController(@Autowired SoapClient soapClient) {
        this.soapClient = soapClient;
    }

    @PostMapping("/sendInvoice")
    public String sendInvoice(@RequestBody InvoiceRequest request) {
        InvoiceResponse response = soapClient.sendInvoice(request);
        return response.getStatus();
    }
}
