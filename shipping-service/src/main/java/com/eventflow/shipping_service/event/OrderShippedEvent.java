package com.eventflow.shipping_service.event;

public class OrderShippedEvent extends BaseEvent {
    private String orderId;

    public OrderShippedEvent(String orderId) {
        super("OrderShipped");
        this.orderId = orderId;
    }

    public String getOrderId() { return orderId; }
}
