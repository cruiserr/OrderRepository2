package edu.iu.c322.orderservice.model.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderDto {
    @NotNull(message = "customerId cannot be empty.")

    private int customerId;

    private String orderDate;
    @NotNull(message = "total cannot be empty.")

    private double total;
    private ShippingDto shippingAddress;
    private List<ItemDto> items;
    private PaymentDto payment;

    public OrderDto(){

    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ShippingDto getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingDto shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public PaymentDto getPayment() {
        return payment;
    }

    public void setPayment(PaymentDto payment) {
        this.payment = payment;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
