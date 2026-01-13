package com.eventflow.notification_service.service;

import com.eventflow.shipping_service.event.OrderShippedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);

    @KafkaListener(topics = "order-shipped", groupId = "notification-group")
    public void notifyUser(OrderShippedEvent event) {
        log.info("NOTIFICATION: Order shipped | orderId={}", event.getOrderId());
    }
}
