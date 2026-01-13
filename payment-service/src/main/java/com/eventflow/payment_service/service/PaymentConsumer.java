package com.eventflow.payment_service.service;

import com.eventflow.order_service.event.OrderCreatedEvent;
import com.eventflow.payment_service.event.PaymentCompletedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PaymentConsumer {

    private static final Logger log = LoggerFactory.getLogger(PaymentConsumer.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentConsumer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-created", groupId = "payment-group")
    public void handleOrder(OrderCreatedEvent event) {
        log.info("Processing payment | orderId={}", event.getOrderId());

        // Simulated payment logic
        log.info("Payment successful | orderId={}", event.getOrderId());

        PaymentCompletedEvent paymentEvent =
                new PaymentCompletedEvent(event.getOrderId(), "SUCCESS");

        kafkaTemplate.send("payment-completed", event.getOrderId(), paymentEvent);
    }
}
