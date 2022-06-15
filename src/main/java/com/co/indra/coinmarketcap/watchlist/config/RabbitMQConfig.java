package com.co.indra.coinmarketcap.watchlist.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue testQueueAlert() {
        return new Queue("alert_notification_data");
    }
    @Bean
    public Queue testQueue() {
        return new Queue("watchlist_coin_queue");
    }
}
