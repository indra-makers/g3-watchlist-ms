package com.co.indra.coinmarketcap.watchlist.messagingQueue;

import com.co.indra.coinmarketcap.watchlist.messagingQueue.model.NotificationQueueBody;
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

    public void sendAlert(NotificationQueueBody notificationQueueBody) {
        try {
            String message = objectMapper.writeValueAsString(notificationQueueBody);
            rabbitTemplate.convertAndSend("alert_notification_data", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
