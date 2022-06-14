package com.co.indra.coinmarketcap.watchlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue testQueue() {
        return new Queue("alert_notification_data");
    }
}
