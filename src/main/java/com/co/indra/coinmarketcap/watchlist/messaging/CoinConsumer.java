package com.co.indra.coinmarketcap.watchlist.messaging;

import com.co.indra.coinmarketcap.watchlist.model.entities.WatchListCoin;
import com.co.indra.coinmarketcap.watchlist.services.WatchListCoinService;
import com.co.indra.coinmarketcap.watchlist.services.WatchListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class CoinConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WatchListService watchListService;

    @RabbitListener(queues = "watchlist_coin_queue")
    public void getUserQueue(String user){
        try {
            WatchListCoin watchListCoin = objectMapper.readValue(user, WatchListCoin.class);
            watchListService.(userNotificationsData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
