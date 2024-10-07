package com.zubak.demo.service;

import com.zubak.demo.model.InvoiceRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class InvoiceValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return InvoiceRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        InvoiceRequest request = (InvoiceRequest) target;

        if (request.getIssueDate() != null && request.getDueDate() != null) {
            if (request.getDueDate().isBefore(request.getIssueDate())) {
                errors.rejectValue("dueDate", "Due date must be after the issue date");
            }
        }
    }
}
