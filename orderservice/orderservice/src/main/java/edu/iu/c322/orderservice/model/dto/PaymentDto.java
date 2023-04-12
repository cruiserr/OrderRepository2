package edu.iu.c322.orderservice.model.dto;

import edu.iu.c322.orderservice.model.entity.BillingAddress;
import jakarta.validation.constraints.NotEmpty;

public class PaymentDto {
    @NotEmpty(message = "method cannot be empty.")

    private String method;

    @NotEmpty(message = "card number cannot be empty.")

    private String number;
    private BillingAddressDto billingAddress;

    public PaymentDto() {
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BillingAddressDto getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddressDto billingAddress) {
        this.billingAddress = billingAddress;
    }
}
