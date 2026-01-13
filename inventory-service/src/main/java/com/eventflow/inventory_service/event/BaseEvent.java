package com.eventflow.inventory_service.event;

import java.time.Instant;
import java.util.UUID;

public abstract class BaseEvent {
    private String eventId = UUID.randomUUID().toString();
    private Instant timestamp = Instant.now();
    private String eventType;

    protected BaseEvent(String eventType) {
        this.eventType = eventType;
    }

    public String getEventId() { return eventId; }
    public Instant getTimestamp() { return timestamp; }
    public String getEventType() { return eventType; }
}

