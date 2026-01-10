package com.eventflow.order_service.event;

public class OrderCreatedEvent extends BaseEvent {
    private String orderId;
    private String product;
    private int quantity;

    public OrderCreatedEvent(String orderId, String product, int quantity) {
        super("OrderCreated");
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
    }

    public String getOrderId() { return orderId; }
}

