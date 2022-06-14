package com.co.indra.coinmarketcap.watchlist.messaging;

import com.co.indra.coinmarketcap.watchlist.messaging.model.QueueBodyNotification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlertProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendAlert(QueueBodyNotification queueBodyNotification) {
        try {
            String message = objectMapper.writeValueAsString(queueBodyNotification);
            rabbitTemplate.convertAndSend("alert_notification_data", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}