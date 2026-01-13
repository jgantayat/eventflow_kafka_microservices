package com.eventflow.order_service.service;

import com.eventflow.order_service.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String createOrder(String product, int quantity) {
        String orderId = UUID.randomUUID().toString();

        log.info("Order created | orderId={} product={} qty={}", orderId, product, quantity);

        OrderCreatedEvent event = new OrderCreatedEvent(orderId, product, quantity);

        kafkaTemplate.send("order-created", orderId, event);
        log.info("OrderCreatedEvent published | eventId={}", event.getEventId());

        return orderId;
    }
}

