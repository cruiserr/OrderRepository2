package edu.iu.c322.orderservice.model.dto;

import jakarta.validation.constraints.NotNull;

public class ReturnDto {

        @NotNull(message = "order id is not valid")
        private int orderId;

        @NotNull(message = "item id is not valid")
        private int itemId;

        private String reason;

        public ReturnDto() {
        }
        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

