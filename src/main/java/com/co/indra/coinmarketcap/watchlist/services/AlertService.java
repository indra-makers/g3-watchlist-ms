package com.co.indra.coinmarketcap.watchlist.services;

import com.co.indra.coinmarketcap.watchlist.messagingQueue.AlertProducer;
import com.co.indra.coinmarketcap.watchlist.messagingQueue.model.Notification;
import com.co.indra.coinmarketcap.watchlist.messagingQueue.model.NotificationQueueBody;
import com.co.indra.coinmarketcap.watchlist.model.entities.CoinPriceAlert;
import com.co.indra.coinmarketcap.watchlist.repositories.CoinPriceAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AlertService {

    @Autowired
    private CoinPriceAlertRepository coinPriceAlertRepository;

    @Autowired
    private AlertProducer alertProducer;

    public void priceAlert(String symbol, Double price){
        List<CoinPriceAlert> coinPriceAlertList = coinPriceAlertRepository.findCoinsPriceAlertBySymbol(symbol);
        for(CoinPriceAlert c: coinPriceAlertList){
            if(price >= c.getGoalPrice() - (c.getGoalPrice()*0.02)&&price<=c.getGoalPrice()+(c.getGoalPrice()*0.02)){
                alertProducer.sendAlert(new NotificationQueueBody("SMS", c.getIdWatchlistCoin(), new Notification("El precio de la moneda ha cambiado", "ALERTA")));
                coinPriceAlertRepository.setIsNotifiedTrue(c);
            }
        }
    }

    public void setAlert(Long idWL, CoinPriceAlert coinPriceAlert){
        coinPriceAlertRepository.addCoinAlertToWatchlist(idWL, coinPriceAlert);
    }
}
