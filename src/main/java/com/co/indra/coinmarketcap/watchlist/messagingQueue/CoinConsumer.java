package com.co.indra.coinmarketcap.watchlist.messagingQueue;

import com.co.indra.coinmarketcap.watchlist.messagingQueue.model.Coin;
import com.co.indra.coinmarketcap.watchlist.services.WatchListAlertService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoinConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WatchListAlertService watchListAlertService;

    @RabbitListener(queues = "watchlist_coin_queue")
    public void getCoinQueue(String coin){
        try {
            Coin coin1 = objectMapper.readValue(coin, Coin.class);
            watchListAlertService.sendPriceAlert(coin1.getSymbol(), coin1.getPrice());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}