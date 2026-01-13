package com.eventflow.payment_service.event;

public class PaymentCompletedEvent extends BaseEvent {
    private String orderId;
    private String status;

    public PaymentCompletedEvent(String orderId, String status) {
        super("PaymentCompleted");
        this.orderId = orderId;
        this.status = status;
    }

    public String getOrderId() { return orderId; }
}

