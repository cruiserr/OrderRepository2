package edu.iu.c322.orderservice.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ItemDto {
    @NotEmpty(message = "name cannot be empty.")

    private String name;
    @NotNull(message = "price cannot be empty.")

    private Double price;

    @NotNull(message = "quantity cannot be empty.")

    private int quantity;

    public ItemDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
