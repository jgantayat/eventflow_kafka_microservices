package com.eventflow.inventory_service.event;

public class InventoryReservedEvent extends BaseEvent {
    private String orderId;

    public InventoryReservedEvent(String orderId) {
        super("InventoryReserved");
        this.orderId = orderId;
    }

    public String getOrderId() { return orderId; }
}

