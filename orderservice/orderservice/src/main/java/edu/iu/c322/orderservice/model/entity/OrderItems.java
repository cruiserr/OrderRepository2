package edu.iu.c322.orderservice.model.entity;

import edu.iu.c322.orderservice.model.dto.OrderItemsDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orderItems")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordItemsGen")
    @SequenceGenerator(name = "ordItemsGen", sequenceName = "orderItemsSeq", allocationSize = 1)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

@NotNull(message = "quantity should not be null")
    private Integer quantity;

    public OrderItems() {
    }

    public OrderItems(Order order, Item item) {
        this.order = order;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}