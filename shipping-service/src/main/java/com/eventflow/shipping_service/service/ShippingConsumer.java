package com.eventflow.shipping_service.service;

import com.eventflow.payment_service.event.PaymentCompletedEvent;
import com.eventflow.shipping_service.event.OrderShippedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShippingConsumer {

    private static final Logger log = LoggerFactory.getLogger(ShippingConsumer.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ShippingConsumer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "payment-completed", groupId = "shipping-group")
    public void shipOrder(PaymentCompletedEvent event) {
        log.info("Shipping order | orderId={}", event.getOrderId());

        log.info("Order shipped | orderId={}", event.getOrderId());

        OrderShippedEvent shippedEvent =
                new OrderShippedEvent(event.getOrderId());

        kafkaTemplate.send("order-shipped", event.getOrderId(), shippedEvent);
    }
}

