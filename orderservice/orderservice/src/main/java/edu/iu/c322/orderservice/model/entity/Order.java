package edu.iu.c322.orderservice.model.entity;

import edu.iu.c322.orderservice.model.dto.OrderDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oiGen")
    @SequenceGenerator(name = "oiGen", sequenceName = "orderIdSeq", allocationSize = 1)
    private int orderId;


    @NotNull(message = "customerId cannot be empty.")

    private int customerId;

    private String orderDate;
    @NotNull(message = "total cannot be empty.")

    private double total;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shippingAddressId", referencedColumnName = "id")
    private Shipping shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentMethodId", referencedColumnName = "id")
    private Payment paymentMethod;

    public Order() {
    }

    public Order(OrderDto orderDto, Shipping shippingAddress, Payment paymentMethod) {
        this.customerId = orderDto.getCustomerId();
        this.total = orderDto.getTotal();
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.orderDate = orderDto.getOrderDate();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Shipping getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Shipping shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Payment getPaymentMethodId() {
        return paymentMethod;
    }

    public void setPaymentMethodId(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Payment getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
