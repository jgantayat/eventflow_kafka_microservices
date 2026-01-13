package com.eventflow.inventory_service.service;

import com.eventflow.inventory_service.event.InventoryReservedEvent;
import com.eventflow.order_service.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumer {

    private static final Logger log = LoggerFactory.getLogger(InventoryConsumer.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public InventoryConsumer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    public void reserveStock(OrderCreatedEvent event) {
        log.info("Reserving inventory | orderId={}", event.getOrderId());

        log.info("Inventory reserved | orderId={}", event.getOrderId());

        InventoryReservedEvent inventoryEvent =
                new InventoryReservedEvent(event.getOrderId());

        kafkaTemplate.send("inventory-reserved", event.getOrderId(), inventoryEvent);
    }
}
