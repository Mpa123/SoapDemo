package com.zubak.demo.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "InvoiceResponse", namespace = "http://example.com/invoices")
public class InvoiceResponse {

    private String status;
    private String invoiceNumber;
}
