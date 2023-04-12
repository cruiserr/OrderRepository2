package edu.iu.c322.orderservice.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BillingAddressDto {
    @NotEmpty(message = "state cannot be empty.")

    private String state;

    @NotEmpty(message = "city cannot be empty.")

    private String city;

    @NotNull(message = "billing postal should not be empty")
    private Integer postalCode;

    public BillingAddressDto() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
}
