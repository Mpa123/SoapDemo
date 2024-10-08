package com.zubak.demo.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@XmlRootElement(name = "InvoiceRequest", namespace = "http://example.com/invoices")
@Getter
@Setter
public class InvoiceRequest {

    @NotBlank(message = "Invoice number cannot be blank")
    private String invoiceNumber;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotNull(message = "Issue date is required")
    private LocalDate issueDate;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    @NotNull(message = "Tax amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Tax must be greater than zero")
    private BigDecimal taxAmount;

    @NotBlank(message = "Status is required")
    private String status;
}
