package edu.iu.c322.orderservice.model.entity;

import edu.iu.c322.orderservice.model.dto.BillingAddressDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "billing")
public class BillingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billingGen")
    @SequenceGenerator(name = "billingGen", sequenceName = "billingSeq", allocationSize = 1)
    private int id;

    @NotEmpty(message = "state cannot be empty.")

    private String state;

    @NotEmpty(message = "city cannot be empty.")

    private String city;

@NotNull(message = "billing postal should not be empty")
    private Integer postalCode;

    public BillingAddress() {
    }

    public BillingAddress(BillingAddressDto billingAddressDto) {
        this.state = billingAddressDto.getState();
        this.city = billingAddressDto.getCity();
        this.postalCode = billingAddressDto.getPostalCode();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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