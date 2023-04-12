package edu.iu.c322.orderservice.model.dto;

import jakarta.validation.constraints.NotNull;

public class OrderItemsDto {
    private Integer itemId;
    @NotNull(message = "quantity should not be null")
    private Integer quantity;

    public OrderItemsDto() {
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
