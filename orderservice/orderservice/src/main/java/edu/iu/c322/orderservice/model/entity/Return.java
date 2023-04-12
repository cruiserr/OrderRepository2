package edu.iu.c322.orderservice.model.entity;

import edu.iu.c322.orderservice.model.dto.ReturnDto;
import jakarta.persistence.*;

@Entity
@Table(name = "returns")
public class Return {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "returnGen")
    @SequenceGenerator(name = "returnGen", sequenceName = "returnSeq", allocationSize = 1)
    private int returnId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private String reason;

    public Return(){

    }
    public Return(Order order, Item item, ReturnDto returnDto) {
        this.order = order;
        this.item = item;
        this.reason = returnDto.getReason();
    }

    public int getReturnId() {
        return returnId;
    }

    public void setReturnId(int returnId) {
        this.returnId = returnId;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
