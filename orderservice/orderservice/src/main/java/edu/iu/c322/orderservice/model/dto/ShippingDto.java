package edu.iu.c322.orderservice.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ShippingDto {

    @NotEmpty(message = "shipping state cannot be empty.")

    private String state;

    @NotEmpty(message = "shipping city cannot be empty.")

    private String city;

    @NotNull(message = "shipping postal should not be empty")

    private int postalCode;

    public ShippingDto() {
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

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
